package com.cnd13natation.performances.swimmer.dao;

import com.cnd13natation.performances.common.dao.RecordRepository;
import com.cnd13natation.performances.swimmer.domain.Swimmer;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/** MongoDB repository for <code>Swimmer</code> */
@Repository
public interface SwimmerRepository extends RecordRepository<Swimmer, String> {

  List<Swimmer> findByFederalIdentifierIn(final List<String> federalIdentifiers);

  Optional<Swimmer> findByFederalIdentifier(final String federalIdentifier);
}
