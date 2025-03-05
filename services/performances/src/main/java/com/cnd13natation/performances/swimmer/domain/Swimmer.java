package com.cnd13natation.performances.swimmer.domain;

import com.cnd13natation.performances.common.domain.Performance;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Swimmer {

  @Id private String federalIdentifier;

  private String name;

  private String firstName;

  private List<Performance> performances;
}
