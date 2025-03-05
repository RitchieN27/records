package com.cnd13natation.performances.batch.performance.writer;

import com.cnd13natation.performances.performance.service.PerformanceService;
import com.cnd13natation.performances.swimmer.domain.Swimmer;
import com.cnd13natation.performances.swimmer.service.SwimmerService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

/**
 * Writer for the extraction job
 *
 * @author Ritchie Nithoo
 */
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
  public void write(Chunk<? extends List<Swimmer>> chunk) {
    // Saving all the swimmers
    final var swimmers = chunk.getItems().getFirst();
    swimmerService.saveInBulk(swimmers);
    performanceService.updateBestPerformances(swimmers);
  }
}
