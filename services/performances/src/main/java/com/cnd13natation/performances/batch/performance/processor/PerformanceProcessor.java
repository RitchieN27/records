package com.cnd13natation.performances.batch.performance.processor;

import com.cnd13natation.performances.batch.common.SwimmerCSV;
import com.cnd13natation.performances.common.domain.Performance;
import com.cnd13natation.performances.performance.mapper.PerformanceMapper;
import com.cnd13natation.performances.swimmer.domain.Swimmer;
import com.cnd13natation.performances.swimmer.service.SwimmerService;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Processor to insert and update all data regarding swimmers and their performances
 *
 * @author Ritchie Nithoo
 */
@Component
@Slf4j
public class PerformanceProcessor implements ItemProcessor<List<SwimmerCSV>, List<Swimmer>> {

  private final SwimmerService swimmerService;

  private final PerformanceMapper performanceMapper;

  public PerformanceProcessor(
      final SwimmerService swimmerService, final PerformanceMapper performanceMapper) {
    this.swimmerService = swimmerService;
    this.performanceMapper = performanceMapper;
  }

  @Override
  public List<Swimmer> process(@NonNull List<SwimmerCSV> swimmersDataFromCSV) {

    final var modifiedOrUpdatedSwimmerByFederalIdentifier = new HashMap<String, Swimmer>();

    final var federalIdentifiersToUpdate =
        swimmersDataFromCSV.stream().map(SwimmerCSV::getFederalIdentifier).toList();

    final var swimmersInDbByFederalIdentifier =
        swimmerService.getAllByFederalIdentifier(federalIdentifiersToUpdate).stream()
            .collect(Collectors.toMap(Swimmer::getFederalIdentifier, Function.identity()));

    final var swimmersInCSVByFederalIdentifier =
        swimmersDataFromCSV.stream()
            .collect(Collectors.groupingBy(SwimmerCSV::getFederalIdentifier));

    swimmersInCSVByFederalIdentifier.forEach(
        (key, value) -> {
          Swimmer swimmer;
          if (swimmersInDbByFederalIdentifier.containsKey(key)) {
            swimmer =
                updateSwimmerFromCSV(
                    swimmersInDbByFederalIdentifier.get(key),
                    swimmersInCSVByFederalIdentifier.get(key));
          } else {
            swimmer = createNewSwimmerFromCSV(swimmersInCSVByFederalIdentifier.get(key));
          }
          modifiedOrUpdatedSwimmerByFederalIdentifier.put(swimmer.getFederalIdentifier(), swimmer);
        });

    return modifiedOrUpdatedSwimmerByFederalIdentifier.values().stream().toList();
  }

  private Swimmer createNewSwimmerFromCSV(final List<SwimmerCSV> swimmersCSV) {
    // Retrieving the first element to extract common information
    final var firstSwimmer = swimmersCSV.getFirst();
    // Creating new swimmer object
    final var createdSwimmerBuilder =
        Swimmer.builder()
            .federalIdentifier(firstSwimmer.getFederalIdentifier())
            .name(firstSwimmer.getName())
            .firstName(firstSwimmer.getFirstName())
            .build();
    // Creating a map containing all best performances by swimming type
    final var allPerformancesBySwimmingTypeFromCSV =
        swimmersCSV.stream()
            .collect(
                Collectors.groupingBy(
                    SwimmerCSV::getSwimmingType,
                    Collectors.minBy(Comparator.comparing(SwimmerCSV::getTime))));
    // Inserting every performance into the swimmer object
    allPerformancesBySwimmingTypeFromCSV
        .values()
        .forEach(
            possiblePerformanceInCSV -> {
              final var performanceInCSV = possiblePerformanceInCSV.orElseThrow();
              createdSwimmerBuilder
                  .getPerformances()
                  .add(performanceMapper.SwimmerCSVToPerformance(performanceInCSV));
            });

    return createdSwimmerBuilder;
  }

  private Swimmer updateSwimmerFromCSV(
      final Swimmer swimmerToUpdate, final List<SwimmerCSV> swimmersCSV) {
    final var performanceBySwimmingType =
        swimmerToUpdate.getPerformances().stream()
            .collect(Collectors.toMap(Performance::getType, Function.identity()));
    // Iterating through all the performances extracted from the CSV
    swimmersCSV.forEach(
        swimmerCSV -> {
          final var performance = performanceBySwimmingType.get(swimmerCSV.getSwimmingType());
          // Updating or inserting new performance for this swimming type
          if (performance == null || swimmerCSV.getTime().compareTo(performance.getTime()) < 0) {
            performanceBySwimmingType.put(
                swimmerCSV.getSwimmingType(),
                performanceMapper.SwimmerCSVToPerformance(swimmerCSV));
          }
        });
    // Replacing all the performances in the swimmer to update
    swimmerToUpdate.setPerformances(performanceBySwimmingType.values().stream().toList());
    return swimmerToUpdate;
  }
}
