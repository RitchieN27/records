package com.cnd13natation.performances.batch.performance.writer;

import com.cnd13natation.performances.performance.service.PerformanceService;
import com.cnd13natation.performances.swimmer.domain.Swimmer;
import com.cnd13natation.performances.swimmer.service.SwimmerService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PerformanceWriter implements ItemWriter<List<Swimmer>> {

  private final SwimmerService swimmerService;

  private final PerformanceService performanceService;

  public PerformanceWriter(
      final SwimmerService swimmerService, final PerformanceService performanceService) {
    this.swimmerService = swimmerService;
    this.performanceService = performanceService;
  }

  @Override
  public void write(Chunk<? extends List<Swimmer>> chunk) throws Exception {
    // Saving all the swimmers
    swimmerService.saveInBulk(chunk.getItems().getFirst());
  }
}
