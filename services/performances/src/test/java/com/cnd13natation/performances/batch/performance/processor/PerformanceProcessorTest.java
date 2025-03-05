package com.cnd13natation.performances.batch.performance.processor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import com.cnd13natation.performances.batch.common.SwimmerCSV;
import com.cnd13natation.performances.performance.mapper.PerformanceMapper;
import com.cnd13natation.performances.performance.mapper.PerformanceMapperImpl;
import com.cnd13natation.performances.swimmer.service.SwimmerService;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
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

  @BeforeAll
  static void setup() {}

  @BeforeEach
  void setupTest() {
    performanceProcessor = new PerformanceProcessor(swimmerService, performanceMapper);
  }

  @Test
  void GivenData_WhenProcessing_thenOK() {
    // Given
    when(swimmerService.getAllByFederalIdentifier(anyList())).thenReturn(List.of());

    // When
    final var processedSwimmers = performanceProcessor.process(swimmersToProcess);
    // Then
    assertThat(processedSwimmers).hasSize(17);
  }
}
