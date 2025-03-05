package com.cnd13natation.performances.batch.performance.reader;

import static org.assertj.core.api.Assertions.assertThat;

import com.cnd13natation.performances.common.domain.PoolSize;
import com.cnd13natation.performances.common.domain.SwimmingType;
import java.time.Duration;
import java.time.LocalDate;
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

  @Test
  void givenData_whenReadingUsingProcessor_thenOk() throws Exception {
    // Given
    // When
    final var extractedSwimmers = performanceReader.read();
    // Then
    assertThat(extractedSwimmers).hasSize(17);
    final var possibleSwimmerToTest =
        extractedSwimmers.stream()
            .filter((swimmerCSV -> swimmerCSV.getFederalIdentifier().equals("976587")))
            .findFirst();
    assertThat(possibleSwimmerToTest).isPresent();
    final var swimmerToTest = possibleSwimmerToTest.get();
    assertThat(swimmerToTest.getFederalIdentifier()).isEqualTo("976587");
    assertThat(swimmerToTest.getName()).isEqualTo("LENCLEN");
    assertThat(swimmerToTest.getFirstName()).isEqualTo("Clara");
    assertThat(swimmerToTest.getSwimmingType()).isEqualTo(SwimmingType.WOMEN_FREESTYLE_50);
    assertThat(swimmerToTest.getPoolSize()).isEqualTo(PoolSize.TWENTY_FIVE);
    assertThat(swimmerToTest.getTime()).isEqualTo(Duration.ofSeconds(30).plusMillis(2));
    assertThat(swimmerToTest.getDate()).isEqualTo(LocalDate.of(2018, 11, 10));
    assertThat(swimmerToTest.getAge()).isEqualTo(23);
    assertThat(swimmerToTest.getEvent()).isEqualTo("Interclubs Toutes Catégories - Poule A");
    assertThat(swimmerToTest.getLocation()).isEqualTo("PARIS");
  }
}
