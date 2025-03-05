package com.cnd13natation.performances.batch.performance.processor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import com.cnd13natation.performances.batch.common.SwimmerCSV;
import com.cnd13natation.performances.common.domain.Performance;
import com.cnd13natation.performances.common.domain.PoolSize;
import com.cnd13natation.performances.common.domain.SwimmingType;
import com.cnd13natation.performances.performance.mapper.PerformanceMapper;
import com.cnd13natation.performances.performance.mapper.PerformanceMapperImpl;
import com.cnd13natation.performances.swimmer.domain.Swimmer;
import com.cnd13natation.performances.swimmer.service.SwimmerService;
import com.cnd13natation.performances.utils.SwimmerCreationTestUtils;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PerformanceProcessorTest {

  private PerformanceProcessor performanceProcessor;

  @Mock private SwimmerService swimmerService;

  private final PerformanceMapper performanceMapper = new PerformanceMapperImpl();

  private List<SwimmerCSV> swimmersToProcess;

  @BeforeEach
  void setupTest() {
    performanceProcessor = new PerformanceProcessor(swimmerService, performanceMapper);
    swimmersToProcess = new ArrayList<>(SwimmerCreationTestUtils.createSwimmerCSV());
  }

  @AfterEach
  void cleanUp() {
    swimmersToProcess.clear();
  }

  @Test
  void givenData_WhenProcessing_thenOK() {
    // Given
    when(swimmerService.getAllByFederalIdentifier(anyList())).thenReturn(List.of());
    // When
    final var processedSwimmers = performanceProcessor.process(swimmersToProcess);
    // Then
    assertThat(processedSwimmers).hasSize(3);
  }

  @Test
  void givenExistingSwimmer_WhenProcessingBetterPerformance_thenReplaceWithNewPerformance() {
    final var swimmerAlreadyInDb =
        Swimmer.builder().federalIdentifier("2681823").name("FEIGE").firstName("Ludovic").build();
    final var breastStrokePerformance =
        Performance.builder()
            .date(LocalDate.of(2017, 11, 11))
            .event("Interclubs Toutes Catégories - Poule B")
            .location("PARIS")
            .type(SwimmingType.MEN_BREASTSTROKE_100)
            .time(Duration.ofMinutes(1).plusSeconds(25).plusMillis(33))
            .swimmingPoolSize(PoolSize.TWENTY_FIVE)
            .build();
    swimmerAlreadyInDb.getPerformances().add(breastStrokePerformance);
    // Given
    when(swimmerService.getAllByFederalIdentifier(anyList()))
        .thenReturn(List.of(swimmerAlreadyInDb));
    // When
    final var processedSwimmers = performanceProcessor.process(swimmersToProcess);
    // Then
    assertThat(processedSwimmers).hasSize(3);
    final var possibleProcessedSwimmerAlreadyInDB =
        processedSwimmers.stream()
            .filter(swimmer -> swimmer.getFederalIdentifier().equals("2681823"))
            .findFirst();
    assertThat(possibleProcessedSwimmerAlreadyInDB).isPresent();
    assertThat(possibleProcessedSwimmerAlreadyInDB.get().getName()).isEqualTo("FEIGE");
    assertThat(possibleProcessedSwimmerAlreadyInDB.get().getFirstName()).isEqualTo("Ludovic");
    assertThat(possibleProcessedSwimmerAlreadyInDB.get().getPerformances()).hasSize(1);
    final var performanceAlreadyInDb =
        possibleProcessedSwimmerAlreadyInDB.get().getPerformances().getFirst();
    assertThat(performanceAlreadyInDb.getType()).isEqualTo(SwimmingType.MEN_BREASTSTROKE_100);
    assertThat(performanceAlreadyInDb.getSwimmingPoolSize()).isEqualTo(PoolSize.TWENTY_FIVE);
    assertThat(performanceAlreadyInDb.getTime())
        .isEqualTo(Duration.ofMinutes(1).plusSeconds(20).plusMillis(33));
    assertThat(performanceAlreadyInDb.getDate()).isEqualTo(LocalDate.of(2018, 11, 11));
    assertThat(performanceAlreadyInDb.getEvent())
        .isEqualTo("Interclubs Toutes Catégories - Poule A");
  }

  @Test
  void givenExistingSwimmer_WhenProcessingWorstPerformance_thenDoNothing() {
    // Given
    final var swimmerAlreadyInDb =
        Swimmer.builder().federalIdentifier("2681823").name("FEIGE").firstName("Ludovic").build();
    final var breastStrokePerformance =
        Performance.builder()
            .date(LocalDate.of(2019, 11, 11))
            .event("Interclubs Toutes Catégories - Poule B")
            .location("PARIS")
            .type(SwimmingType.MEN_BREASTSTROKE_100)
            .time(Duration.ofMinutes(1).plusSeconds(18).plusMillis(33))
            .swimmingPoolSize(PoolSize.TWENTY_FIVE)
            .build();
    swimmerAlreadyInDb.getPerformances().add(breastStrokePerformance);
    when(swimmerService.getAllByFederalIdentifier(anyList()))
        .thenReturn(List.of(swimmerAlreadyInDb));
    // When
    final var processedSwimmers = performanceProcessor.process(swimmersToProcess);
    // Then
    assertThat(processedSwimmers).hasSize(3);
    final var possibleProcessedSwimmerAlreadyInDB =
        processedSwimmers.stream()
            .filter(swimmer -> swimmer.getFederalIdentifier().equals("2681823"))
            .findFirst();
    assertThat(possibleProcessedSwimmerAlreadyInDB).isPresent();
    assertThat(possibleProcessedSwimmerAlreadyInDB.get().getName()).isEqualTo("FEIGE");
    assertThat(possibleProcessedSwimmerAlreadyInDB.get().getFirstName()).isEqualTo("Ludovic");
    assertThat(possibleProcessedSwimmerAlreadyInDB.get().getPerformances()).hasSize(1);
    final var performanceAlreadyInDb =
        possibleProcessedSwimmerAlreadyInDB.get().getPerformances().getFirst();
    assertThat(performanceAlreadyInDb.getType()).isEqualTo(SwimmingType.MEN_BREASTSTROKE_100);
    assertThat(performanceAlreadyInDb.getSwimmingPoolSize()).isEqualTo(PoolSize.TWENTY_FIVE);
    assertThat(performanceAlreadyInDb.getTime())
        .isEqualTo(Duration.ofMinutes(1).plusSeconds(18).plusMillis(33));
    assertThat(performanceAlreadyInDb.getDate()).isEqualTo(LocalDate.of(2019, 11, 11));
    assertThat(performanceAlreadyInDb.getEvent())
        .isEqualTo("Interclubs Toutes Catégories - Poule B");
  }

  @Test
  void givenNonExistingSwimmer_WhenProcessingTwoPerformances_thenKeepBestPerformance() {
    // Given
    when(swimmerService.getAllByFederalIdentifier(anyList())).thenReturn(List.of());
    final var secondPerformanceSwimmer =
        SwimmerCSV.builder()
            .federalIdentifier("2681823")
            .name("FEIGE")
            .firstName("Ludovic")
            .swimmingType(SwimmingType.MEN_BREASTSTROKE_100)
            .poolSize(PoolSize.TWENTY_FIVE)
            .time(Duration.ofMinutes(1).plusSeconds(15).plusMillis(33))
            .date(LocalDate.of(2020, 11, 11))
            .age(26)
            .event("Interclubs Toutes Catégories - Poule C")
            .location("PARIS")
            .build();
    swimmersToProcess.add(secondPerformanceSwimmer);
    // When
    final var processedSwimmers = performanceProcessor.process(swimmersToProcess);
    // Then
    assertThat(processedSwimmers).hasSize(3);
    final var possibleProcessedSwimmerWithTwoPerformances =
        processedSwimmers.stream()
            .filter(swimmer -> swimmer.getFederalIdentifier().equals("2681823"))
            .findFirst();
    assertThat(possibleProcessedSwimmerWithTwoPerformances).isPresent();
    assertThat(possibleProcessedSwimmerWithTwoPerformances.get().getName()).isEqualTo("FEIGE");
    assertThat(possibleProcessedSwimmerWithTwoPerformances.get().getFirstName())
        .isEqualTo("Ludovic");
    assertThat(possibleProcessedSwimmerWithTwoPerformances.get().getPerformances())
        .isNotNull()
        .hasSize(1);
    final var onlyPerformance =
        possibleProcessedSwimmerWithTwoPerformances.get().getPerformances().getFirst();
    assertThat(onlyPerformance.getType()).isEqualTo(SwimmingType.MEN_BREASTSTROKE_100);
    assertThat(onlyPerformance.getSwimmingPoolSize()).isEqualTo(PoolSize.TWENTY_FIVE);
    assertThat(onlyPerformance.getTime())
        .isEqualTo(Duration.ofMinutes(1).plusSeconds(15).plusMillis(33));
    assertThat(onlyPerformance.getDate()).isEqualTo(LocalDate.of(2020, 11, 11));
    assertThat(onlyPerformance.getEvent()).isEqualTo("Interclubs Toutes Catégories - Poule C");
  }

  @Test
  void givenExistingSwimmer_WhenProcessingNewPerformance_ThenAddNewPerformance() {
    // Given
    final var swimmerAlreadyInDb =
        Swimmer.builder().federalIdentifier("2681823").name("FEIGE").firstName("Ludovic").build();
    final var breastStrokePerformance =
        Performance.builder()
            .date(LocalDate.of(2019, 11, 11))
            .event("Interclubs Toutes Catégories - Poule B")
            .location("PARIS")
            .type(SwimmingType.MEN_BREASTSTROKE_100)
            .time(Duration.ofMinutes(1).plusSeconds(18).plusMillis(33))
            .swimmingPoolSize(PoolSize.TWENTY_FIVE)
            .build();
    swimmerAlreadyInDb.getPerformances().add(breastStrokePerformance);
    when(swimmerService.getAllByFederalIdentifier(anyList()))
        .thenReturn(List.of(swimmerAlreadyInDb));
    final var secondPerformanceSwimmer =
        SwimmerCSV.builder()
            .federalIdentifier("2681823")
            .name("FEIGE")
            .firstName("Ludovic")
            .swimmingType(SwimmingType.MEN_BUTTERFLY_50)
            .poolSize(PoolSize.FIFTY)
            .time(Duration.ofSeconds(28).plusMillis(33))
            .date(LocalDate.of(2025, 6, 10))
            .age(32)
            .event("Meeting de fou")
            .location("PARIS")
            .build();
    swimmersToProcess.add(secondPerformanceSwimmer);
    // When
    final var processedSwimmers = performanceProcessor.process(swimmersToProcess);
    // Then
    assertThat(processedSwimmers).hasSize(3);
    final var possibleProcessedSwimmerWithTwoPerformances =
        processedSwimmers.stream()
            .filter(swimmer -> swimmer.getFederalIdentifier().equals("2681823"))
            .findFirst();
    assertThat(possibleProcessedSwimmerWithTwoPerformances).isPresent();
    assertThat(possibleProcessedSwimmerWithTwoPerformances.get().getName()).isEqualTo("FEIGE");
    assertThat(possibleProcessedSwimmerWithTwoPerformances.get().getFirstName())
        .isEqualTo("Ludovic");
    assertThat(possibleProcessedSwimmerWithTwoPerformances.get().getPerformances())
        .isNotNull()
        .hasSize(2);
    final var possibleNewPerformance =
        possibleProcessedSwimmerWithTwoPerformances.get().getPerformances().stream()
            .filter(performance -> SwimmingType.MEN_BUTTERFLY_50.equals(performance.getType()))
            .findFirst();
    assertThat(possibleNewPerformance).isPresent();
    final var newPerformance = possibleNewPerformance.get();
    assertThat(newPerformance.getType()).isEqualTo(SwimmingType.MEN_BUTTERFLY_50);
    assertThat(newPerformance.getSwimmingPoolSize()).isEqualTo(PoolSize.FIFTY);
    assertThat(newPerformance.getTime()).isEqualTo(Duration.ofSeconds(28).plusMillis(33));
    assertThat(newPerformance.getDate()).isEqualTo(LocalDate.of(2025, 6, 10));
    assertThat(newPerformance.getEvent()).isEqualTo("Meeting de fou");
    assertThat(newPerformance.getLocation()).isEqualTo("PARIS");
  }
}
