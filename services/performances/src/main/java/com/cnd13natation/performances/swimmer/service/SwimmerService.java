package com.cnd13natation.performances.swimmer.service;

import com.cnd13natation.performances.common.exception.SwimmerNotFoundException;
import com.cnd13natation.performances.swimmer.dao.SwimmerRepository;
import com.cnd13natation.performances.swimmer.domain.Swimmer;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SwimmerService {

  private final SwimmerRepository swimmerRepository;

  public SwimmerService(SwimmerRepository swimmerRepository) {
    this.swimmerRepository = swimmerRepository;
  }

  public Swimmer getOne(final String federalIdentifier) throws SwimmerNotFoundException {
    return this.swimmerRepository
        .findByFederalIdentifier(federalIdentifier)
        .orElseThrow(
            () ->
                new SwimmerNotFoundException(
                    "There was no swimmer found with the identifier %s"
                        .formatted(federalIdentifier)));
  }

  public List<Swimmer> getAllByFederalIdentifier(List<String> federalIdentifiers) {
    return this.swimmerRepository.findByFederalIdentifierIn(federalIdentifiers);
  }
}
