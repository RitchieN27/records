package com.cnd13natation.performances.common.dao;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Common spring interface for repositories Add any custom methods needed for all repositories here
 *
 * @param <T> The object being manipulated by the repository
 * @param <I> The type of identifiers
 */
@Repository
public interface RecordRepository<T, I extends Serializable>
    extends CrudRepository<T, I> {}
