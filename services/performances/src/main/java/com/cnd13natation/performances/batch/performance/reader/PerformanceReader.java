package com.cnd13natation.performances.batch.performance.reader;

import com.cnd13natation.performances.batch.common.SwimmerCSV;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PerformanceReader implements ItemReader<List<SwimmerCSV>> {

  @Override
  public List<SwimmerCSV> read() {
    FlatFileItemReader<SwimmerCSV> itemReader = new FlatFileItemReader<>();
    itemReader.setResource(new FileSystemResource("resources/records.csv"));
    DefaultLineMapper<SwimmerCSV> lineMapper = new DefaultLineMapper<>();
    lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
    lineMapper.setFieldSetMapper(new SwimmerFieldSetMapper());
    itemReader.setLineMapper(lineMapper);
    itemReader.open(new ExecutionContext());

    return List.of();
  }
}
