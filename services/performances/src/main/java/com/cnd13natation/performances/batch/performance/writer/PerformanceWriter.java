package com.cnd13natation.performances.batch.performance.writer;

import com.cnd13natation.performances.swimmer.domain.Swimmer;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PerformanceWriter implements ItemWriter<List<Swimmer>> {

  @Override
  public void write(Chunk<? extends List<Swimmer>> chunk) throws Exception {}
}
