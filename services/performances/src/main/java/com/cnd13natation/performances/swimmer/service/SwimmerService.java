package com.cnd13natation.performances.swimmer.service;

import com.cnd13natation.performances.swimmer.dao.SwimmerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SwimmerService {

  private final SwimmerRepository swimmerRepository;

  public SwimmerService(SwimmerRepository swimmerRepository) {
    this.swimmerRepository = swimmerRepository;
  }
}
