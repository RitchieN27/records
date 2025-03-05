package com.cnd13natation.performances.batch.common;

import com.cnd13natation.performances.common.domain.PoolSize;
import com.cnd13natation.performances.common.domain.SwimmingType;
import java.time.Duration;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SwimmerCSV {

  private String federalIdentifier;

  private String name;

  private String firstName;

  private SwimmingType swimmingType;

  private PoolSize poolSize;

  private Duration time;

  private LocalDate date;

  private Integer age;

  private String event;

  private String location;

  public SwimmerCSV(
      final String federalIdentifier,
      final String name,
      final String firstName,
      final SwimmingType swimmingType,
      final PoolSize poolSize,
      final Duration time,
      final LocalDate date,
      final int age,
      final String event,
      final String location) {
    this.federalIdentifier = federalIdentifier;
    this.name = name;
    this.firstName = firstName;
    this.swimmingType = swimmingType;
    this.poolSize = poolSize;
    this.time = time;
    this.date = date;
    this.age = age;
    this.event = event;
    this.location = location;
  }
}
