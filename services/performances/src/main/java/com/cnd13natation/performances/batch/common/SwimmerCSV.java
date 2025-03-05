package com.cnd13natation.performances.batch.common;

import com.cnd13natation.performances.common.domain.PoolSize;
import com.cnd13natation.performances.common.domain.SwimmingType;
import java.time.Duration;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Class representing a line in the CSV file used to update the data in the database
 *
 * @author Ritchie Nithoo
 */
@Data
@Builder
@AllArgsConstructor
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
}
