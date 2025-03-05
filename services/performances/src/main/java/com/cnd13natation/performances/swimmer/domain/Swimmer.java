package com.cnd13natation.performances.swimmer.domain;

import com.cnd13natation.performances.common.domain.Performance;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Classes representing a swimmer
 *
 * @author Ritchie Nithoo
 */
@Data
@Builder
public class Swimmer {

  @Id private String federalIdentifier;

  private String name;

  private String firstName;

  @Builder.Default private List<Performance> performances = new ArrayList<>();
}
