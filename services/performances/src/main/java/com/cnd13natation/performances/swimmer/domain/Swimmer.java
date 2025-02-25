package com.cnd13natation.performances.swimmer.domain;

import com.cnd13natation.performances.common.domain.Performance;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Swimmer {

  @Id private String id;

  private String federalIdentifier;

  private String name;

  private String firstName;

  private List<Performance> performances;
}
