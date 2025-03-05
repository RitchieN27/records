package com.cnd13natation.performances.performance.mapper;

import com.cnd13natation.performances.batch.common.SwimmerCSV;
import com.cnd13natation.performances.common.domain.Performance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PerformanceMapper {

  @Mapping(target = "type", source = "swimmingType")
  @Mapping(target = "swimmingPoolSize", source = "PoolSize")
  Performance SwimmerCSVToPerformance(SwimmerCSV swimmerCSV);
}
