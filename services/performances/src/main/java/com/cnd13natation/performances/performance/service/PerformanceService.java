package com.cnd13natation.performances.performance.service;

import com.cnd13natation.performances.performance.dao.PerformanceRepository;
import com.cnd13natation.performances.performance.domain.BestPerformance;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PerformanceService {

  private final PerformanceRepository performanceRepository;

  @Autowired
  public PerformanceService(PerformanceRepository performanceRepository) {
    this.performanceRepository = performanceRepository;
  }

  public List<BestPerformance> getAll() {
    final var allBestPerformances = new ArrayList<BestPerformance>();
    this.performanceRepository.findAll().forEach(allBestPerformances::add);
    return allBestPerformances;
  }
}
