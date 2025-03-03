package com.cnd13natation.performances.performance.dao;

import com.cnd13natation.performances.common.dao.RecordRepository;
import com.cnd13natation.performances.performance.domain.BestPerformance;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends RecordRepository<BestPerformance, String> {}
