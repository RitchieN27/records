package com.cnd13natation.performances.rest;

import com.cnd13natation.performances.common.exception.SwimmerNotFoundException;
import com.cnd13natation.performances.common.rest.ApiConstants;
import com.cnd13natation.performances.swimmer.domain.Swimmer;
import com.cnd13natation.performances.swimmer.service.SwimmerService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to manage swimmers
 *
 * @author Ritchie Nithoo
 */
@RestController
@RequestMapping(ApiConstants.PATH_SWIMMER)
@Slf4j
public class SwimmerController {

  private final SwimmerService swimmerService;

  @Autowired
  public SwimmerController(final SwimmerService swimmerService) {
    this.swimmerService = swimmerService;
  }

  @GetMapping(ApiConstants.PATH_IDENTIFIER)
  public Swimmer getOne(@PathVariable final String identifier) throws SwimmerNotFoundException {
    return this.swimmerService.getOne(identifier);
  }

  @GetMapping(ApiConstants.PATH_BY_FEDERAL_IDENTIFIER)
  public List<Swimmer> getAllByFederalIdentifiers(@RequestParam List<String> federalIdentifiers) {
    return this.swimmerService.getAllByFederalIdentifier(federalIdentifiers);
  }
}
