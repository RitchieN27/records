package com.cnd13natation.performances.batch.performance.reader;

import com.cnd13natation.performances.batch.common.SwimmerCSV;
import com.cnd13natation.performances.common.domain.PoolSize;
import com.cnd13natation.performances.common.domain.SwimmingType;
import jakarta.annotation.Nonnull;
import java.time.Duration;
import java.time.LocalDate;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class SwimmerFieldSetMapper implements FieldSetMapper<SwimmerCSV> {

  @Override
  public SwimmerCSV mapFieldSet(@Nonnull FieldSet fieldSet) {
    return SwimmerCSV.builder()
        .federalIdentifier(fieldSet.readString(0))
        .name(fieldSet.readString(1))
        .firstName(fieldSet.readString(2))
        .swimmingType(SwimmingType.valueOf(fieldSet.readString(3)))
        .poolSize(PoolSize.valueOf(fieldSet.readString(4)))
        .time(Duration.parse(fieldSet.readString(5)))
        .date(LocalDate.parse(fieldSet.readString(6)))
        .age(Integer.parseInt(fieldSet.readString(7)))
        .event(fieldSet.readString(8))
        .location(fieldSet.readString(9))
        .build();
  }
}
