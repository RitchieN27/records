package com.cnd13natation.performances.rest;

import com.cnd13natation.performances.common.rest.ApiConstants;
import com.cnd13natation.performances.performance.domain.BestPerformance;
import com.cnd13natation.performances.performance.service.PerformanceService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(ApiConstants.PATH_BEST_PERFORMANCE)
@Slf4j
public class PerformanceController {

  private final PerformanceService performanceService;

  @Autowired
  public PerformanceController(PerformanceService performanceService) {
    this.performanceService = performanceService;
  }

  @GetMapping
  public List<BestPerformance> getAll() {
    return this.performanceService.getAll();
  }
}
