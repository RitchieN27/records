package com.cnd13natation.performances.performance.service;

import com.cnd13natation.performances.performance.dao.PerformanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PerformanceServiceTest {

  private PerformanceService performanceService;

  @Mock private PerformanceRepository performanceRepository;

  @BeforeEach
  void setupTest() {
    performanceService = new PerformanceService(performanceRepository);
  }

  @Test
  void givenExistingPerformance_WhenNotBetterPerformance_ThenDoNothing() {
    // Given

    // When

    // Then
  }

  @Test
  void givenExistingPerformance_WhenBetterPerformance_ThenUpdatePerformance() {}

  @Test
  void givenNoPerformance_WhenNewPerformance_ThenAddNewPerformance() {}
}
