package com.cnd13natation.performances.swimmer.dao;

import com.cnd13natation.performances.swimmer.domain.Swimmer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SwimmerRepository extends MongoRepository<Swimmer, String> {
}
