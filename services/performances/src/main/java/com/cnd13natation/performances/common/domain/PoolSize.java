package com.cnd13natation.performances.common.domain;

import java.util.Optional;

/**
 * Enumeration for the pool size
 *
 * @author Ritchie Nithoo
 */
public enum PoolSize {
  TWENTY_FIVE("25"),
  FIFTY("50");

  public final String label;

  PoolSize(String label) {
    this.label = label;
  }

  public static Optional<PoolSize> fromLabel(String label) {
    for (PoolSize poolSize : PoolSize.values()) {
      if (poolSize.label.equals(label)) {
        return Optional.of(poolSize);
      }
    }
    return Optional.empty();
  }
}
