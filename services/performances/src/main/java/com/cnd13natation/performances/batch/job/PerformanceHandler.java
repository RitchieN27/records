package com.cnd13natation.performances.batch.job;

import com.cnd13natation.performances.batch.common.SwimmerCSV;
import com.cnd13natation.performances.swimmer.domain.Swimmer;
import java.util.List;
import lombok.Getter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

/**
 * Handler class to manage reader, writer and processor for the extraction job
 *
 * @author Ritchie Nithoo
 */
@Getter
@Component
public class PerformanceHandler {

  private final ItemReader<List<SwimmerCSV>> performanceItemReader;

  private final ItemWriter<List<Swimmer>> performanceItemWriter;

  private final ItemProcessor<List<SwimmerCSV>, List<Swimmer>> performanceItemProcessor;

  public PerformanceHandler(
      final ItemReader<List<SwimmerCSV>> performanceItemReader,
      final ItemWriter<List<Swimmer>> performanceItemWriter,
      final ItemProcessor<List<SwimmerCSV>, List<Swimmer>> performanceItemProcessor) {
    this.performanceItemReader = performanceItemReader;
    this.performanceItemWriter = performanceItemWriter;
    this.performanceItemProcessor = performanceItemProcessor;
  }
}
