package com.cnd13natation.performances.batch.performance.processor;

import com.cnd13natation.performances.batch.common.SwimmerCSV;
import com.cnd13natation.performances.swimmer.domain.Swimmer;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PerformanceProcessor implements ItemProcessor<List<SwimmerCSV>, List<Swimmer>> {
  @Override
  public List<Swimmer> process(List<SwimmerCSV> item) throws Exception {
    return List.of();
  }
}
