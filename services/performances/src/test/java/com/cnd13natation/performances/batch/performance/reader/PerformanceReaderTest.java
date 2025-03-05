package com.cnd13natation.performances.batch.performance.reader;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class PerformanceReaderTest {

  private PerformanceReader performanceReader;

  @BeforeEach
  void setup() {
    performanceReader = new PerformanceReader();
    ReflectionTestUtils.setField(
        performanceReader, "fileDataPath", "src/test/resources/records.csv");
  }

  @AfterEach
  void cleanUp() {}

  @Test
  void givenData_whenReadingUsingProcessor_thenOk() throws Exception {
    // Given
    // When
    final var extractedSwimmers = performanceReader.read();
    // Then
    assertThat(extractedSwimmers).hasSize(17);
  }
}
