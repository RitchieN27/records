package com.cnd13natation.performances.performance.domain;

import com.cnd13natation.performances.common.domain.Performance;
import lombok.Data;
import org.springframework.data.annotation.Id;

/** Best performance */
@Data
public class BestPerformance {
  @Id private String id;

  private Performance performance;

  private String federalIdentifier;
}
