package com.cnd13natation.performances.common.domain;

import java.time.Duration;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

/**
 * Class representing a swimmer's performance
 *
 * @author Ritchie Nithoo
 */
@Data
@Builder
public class Performance {

  private LocalDate date;

  private String event;

  private String location;

  private SwimmingType type;

  private Duration time;

  private PoolSize swimmingPoolSize;
}
