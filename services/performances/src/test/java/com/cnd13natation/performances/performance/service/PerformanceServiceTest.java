package com.cnd13natation.performances.performance.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cnd13natation.performances.common.domain.Performance;
import com.cnd13natation.performances.common.domain.PoolSize;
import com.cnd13natation.performances.common.domain.SwimmingType;
import com.cnd13natation.performances.performance.dao.PerformanceRepository;
import com.cnd13natation.performances.performance.domain.BestPerformance;
import com.cnd13natation.performances.swimmer.domain.Swimmer;
import com.cnd13natation.performances.utils.SwimmerCreationTestUtils;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PerformanceServiceTest {

  private PerformanceService performanceService;

  @Mock private PerformanceRepository performanceRepository;

  @Captor private ArgumentCaptor<Collection<BestPerformance>> bestPerformanceCaptor;

  private List<Swimmer> swimmersToProcess;

  @BeforeEach
  void setupTest() {
    performanceService = new PerformanceService(performanceRepository);
    swimmersToProcess = new ArrayList<>(SwimmerCreationTestUtils.createSwimmers());
  }

  @AfterEach
  void cleanUp() {
    this.swimmersToProcess.clear();
  }

  @Test
  void givenExistingPerformance_WhenNotBetterPerformance_ThenDoNothing() {
    // Given
    final var existingBestPerformance =
        BestPerformance.builder().federalIdentifier("2393827").build();
    final var existingPerformance =
        Performance.builder()
            .date(LocalDate.of(2016, 6, 11))
            .event("Championnat Régional d'été (50m)")
            .location("SARCELLES")
            .type(SwimmingType.WOMEN_FREESTYLE_50)
            .time(Duration.ofSeconds(27).plusMillis(39))
            .swimmingPoolSize(PoolSize.TWENTY_FIVE)
            .build();
    existingBestPerformance.setPerformance(existingPerformance);
    when(performanceRepository.findAll()).thenReturn(List.of(existingBestPerformance));
    // When
    performanceService.updateBestPerformances(swimmersToProcess);
    verify(performanceRepository).saveAll(bestPerformanceCaptor.capture());
    final var bestPerformances = bestPerformanceCaptor.getValue();
    // Then
    assertThat(bestPerformances).isNotNull().isNotEmpty().hasSize(2);
    final var bestPerformancesBySwimmingType =
        bestPerformances.stream()
            .collect(
                Collectors.toMap(
                    bestPerformance -> bestPerformance.getPerformance().getType(),
                    Function.identity()));
    assertThat(bestPerformancesBySwimmingType.get(SwimmingType.WOMEN_FREESTYLE_50)).isNotNull();
    final var womenFreestyle50BestPerformance =
        bestPerformancesBySwimmingType.get(SwimmingType.WOMEN_FREESTYLE_50);
    assertThat(womenFreestyle50BestPerformance.getFederalIdentifier()).isEqualTo("2393827");
  }

  @Test
  void givenExistingPerformance_WhenBetterPerformance_ThenUpdatePerformance() {
    // Given
    final var existingBestPerformance =
        BestPerformance.builder().federalIdentifier("2393827").build();
    final var existingPerformance =
        Performance.builder()
            .date(LocalDate.of(2016, 6, 11))
            .event("Championnat Régional d'été (50m)")
            .location("SARCELLES")
            .type(SwimmingType.WOMEN_FREESTYLE_50)
            .time(Duration.ofSeconds(36).plusMillis(39))
            .swimmingPoolSize(PoolSize.TWENTY_FIVE)
            .build();
    existingBestPerformance.setPerformance(existingPerformance);
    when(performanceRepository.findAll()).thenReturn(List.of(existingBestPerformance));
    // When
    performanceService.updateBestPerformances(swimmersToProcess);
    verify(performanceRepository).saveAll(bestPerformanceCaptor.capture());
    final var bestPerformances = bestPerformanceCaptor.getValue();
    // Then
    assertThat(bestPerformances).isNotNull().isNotEmpty().hasSize(2);
    final var bestPerformancesBySwimmingType =
        bestPerformances.stream()
            .collect(
                Collectors.toMap(
                    bestPerformance -> bestPerformance.getPerformance().getType(),
                    Function.identity()));
    assertThat(bestPerformancesBySwimmingType.get(SwimmingType.WOMEN_FREESTYLE_50)).isNotNull();
    final var womenFreestyle50BestPerformance =
        bestPerformancesBySwimmingType.get(SwimmingType.WOMEN_FREESTYLE_50);
    assertThat(womenFreestyle50BestPerformance.getFederalIdentifier()).isEqualTo("976587");
    assertThat(womenFreestyle50BestPerformance.getPerformance().getTime())
        .isEqualTo(Duration.ofSeconds(30).plusMillis(2));
  }

  @Test
  void givenNoPerformance_WhenNewPerformance_ThenAddNewPerformance() {
    // When
    performanceService.updateBestPerformances(swimmersToProcess);
    verify(performanceRepository).saveAll(bestPerformanceCaptor.capture());
    final var bestPerformances = bestPerformanceCaptor.getValue();
    // Then
    assertThat(bestPerformances).isNotNull().isNotEmpty().hasSize(2);
    final var bestPerformancesBySwimmingType =
        bestPerformances.stream()
            .collect(
                Collectors.toMap(
                    bestPerformance -> bestPerformance.getPerformance().getType(),
                    Function.identity()));
    assertThat(bestPerformancesBySwimmingType.get(SwimmingType.WOMEN_FREESTYLE_50)).isNotNull();
    assertThat(
            bestPerformancesBySwimmingType
                .get(SwimmingType.WOMEN_FREESTYLE_50)
                .getFederalIdentifier())
        .isEqualTo("976587");
    assertThat(bestPerformancesBySwimmingType.get(SwimmingType.MEN_BREASTSTROKE_100)).isNotNull();
    assertThat(
            bestPerformancesBySwimmingType
                .get(SwimmingType.MEN_BREASTSTROKE_100)
                .getFederalIdentifier())
        .isEqualTo("2681823");
  }
}
