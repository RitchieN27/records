package com.cnd13natation.performances.batch.performance.reader;

import com.cnd13natation.performances.batch.common.SwimmerCSV;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PerformanceReader implements ItemReader<List<SwimmerCSV>> {

  @Override
  public List<SwimmerCSV> read() {
    return List.of();
  }
}
