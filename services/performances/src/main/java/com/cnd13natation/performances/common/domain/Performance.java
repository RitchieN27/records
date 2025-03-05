package com.cnd13natation.performances.common.domain;

import java.time.Duration;
import java.time.LocalDate;
import lombok.Data;

@Data
public class Performance {

  private LocalDate date;

  private String event;

  private String location;

  private SwimmingType type;

  private Duration time;

  private PoolSize swimmingPoolSize;
}
