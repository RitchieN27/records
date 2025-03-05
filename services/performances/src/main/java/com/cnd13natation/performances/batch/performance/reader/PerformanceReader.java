package com.cnd13natation.performances.batch.performance.reader;

import com.cnd13natation.performances.batch.common.SwimmerCSV;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 * File CSV reader for the extraction job
 *
 * @author Ritchie Nithoo
 */
@Component
@Slf4j
public class PerformanceReader implements ItemReader<List<SwimmerCSV>> {

  @Value("${records.data.path}")
  private String fileDataPath;

  @Override
  public List<SwimmerCSV> read() throws Exception {
    final var allSwimmers = new ArrayList<SwimmerCSV>();
    FlatFileItemReader<SwimmerCSV> itemReader = new FlatFileItemReader<>();
    itemReader.setResource(new FileSystemResource(fileDataPath));
    DefaultLineMapper<SwimmerCSV> lineMapper = new DefaultLineMapper<>();
    lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
    lineMapper.setFieldSetMapper(new SwimmerFieldSetMapper());
    itemReader.setLineMapper(lineMapper);
    itemReader.setLinesToSkip(1);
    // Opening the item reader to read all the lines
    itemReader.open(new ExecutionContext());
    var aSwimmer = itemReader.read();
    while (aSwimmer != null) {
      allSwimmers.add(aSwimmer);
      aSwimmer = itemReader.read();
    }
    return allSwimmers;
  }
}
