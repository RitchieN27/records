package com.cnd13natation.performances.performance.domain;

import com.cnd13natation.performances.common.domain.Performance;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Class representing the best performance
 *
 * @author Ritchie Nithoo
 */
@Data
@Builder
public class BestPerformance {
  @Id private String id;

  private Performance performance;

  private String federalIdentifier;
}
