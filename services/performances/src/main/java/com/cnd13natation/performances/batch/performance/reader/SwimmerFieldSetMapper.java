package com.cnd13natation.performances.batch.performance.reader;

import com.cnd13natation.performances.batch.common.SwimmerCSV;
import com.cnd13natation.performances.common.domain.PoolSize;
import com.cnd13natation.performances.common.domain.SwimmingType;
import com.cnd13natation.utils.DateUtils;
import java.time.Duration;
import java.time.LocalDate;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.lang.NonNull;

public class SwimmerFieldSetMapper implements FieldSetMapper<SwimmerCSV> {

  @Override
  public SwimmerCSV mapFieldSet(@NonNull FieldSet fieldSet) {
    return SwimmerCSV.builder()
        .federalIdentifier(fieldSet.readString(0))
        .name(fieldSet.readString(1))
        .firstName(fieldSet.readString(2))
        .swimmingType(SwimmingType.fromLabel(fieldSet.readString(3)).orElse(null))
        .poolSize(PoolSize.fromLabel(fieldSet.readString(4)).orElse(null))
        .time(getDurationFromFile(fieldSet.readString(5)))
        .date(LocalDate.parse(fieldSet.readString(6), DateUtils.FR_DATE_FORMATTER))
        .age(Integer.parseInt(fieldSet.readString(7)))
        .event(fieldSet.readString(8))
        .location(fieldSet.readString(9))
        .build();
  }

  private Duration getDurationFromFile(final String duration) {
    final var splittedString = duration.split("\\.");
    final var minutes = Integer.parseInt(splittedString[0]);
    final var seconds = Integer.parseInt(splittedString[1].substring(0, 2));
    final var milliseconds = Integer.parseInt(splittedString[1].substring(2));
    return Duration.ofMinutes(minutes).plusSeconds(seconds).plusMillis(milliseconds);
  }
}
