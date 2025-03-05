package com.cnd13natation.performances.performance.service;

import com.cnd13natation.performances.performance.dao.PerformanceRepository;
import com.cnd13natation.performances.performance.domain.BestPerformance;
import com.cnd13natation.performances.swimmer.domain.Swimmer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to manage {@link BestPerformance}
 *
 * @author Ritchie Nithoo
 */
@Service
@Slf4j
public class PerformanceService {

  private final PerformanceRepository performanceRepository;

  @Autowired
  public PerformanceService(PerformanceRepository performanceRepository) {
    this.performanceRepository = performanceRepository;
  }

  public List<BestPerformance> getAll() {
    final var allBestPerformances = new ArrayList<BestPerformance>();
    this.performanceRepository.findAll().forEach(allBestPerformances::add);
    return allBestPerformances;
  }

  /**
   * This method updates the best performances using a list of swimmers and their performances
   *
   * @param swimmers The swimmers with their performances to update the best times
   */
  public void updateBestPerformances(List<Swimmer> swimmers) {
    // Getting all the best performances and sorting them by type
    final var bestPerformancesBySwimmingType =
        this.getAll().stream()
            .collect(
                Collectors.toMap(
                    bestPerformance -> bestPerformance.getPerformance().getType(),
                    Function.identity()));
    // Updating the information with the best performances
    swimmers.forEach(
        swimmer ->
            swimmer
                .getPerformances()
                .forEach(
                    performance ->
                        bestPerformancesBySwimmingType.merge(
                            performance.getType(),
                            BestPerformance.builder()
                                .performance(performance)
                                .federalIdentifier(swimmer.getFederalIdentifier())
                                .build(),
                            (existing, newOne) ->
                                existing
                                            .getPerformance()
                                            .getTime()
                                            .compareTo(newOne.getPerformance().getTime())
                                        < 0
                                    ? existing
                                    : newOne)));
    // Save to repository
    this.performanceRepository.saveAll(bestPerformancesBySwimmingType.values());
  }
}
