package com.cnd13natation.performances.common.exception;

/**
 * Custom exception when swimmer is not found
 *
 * @author Ritchie Nithoo
 */
public class SwimmerNotFoundException extends Exception {

  public SwimmerNotFoundException(final String errorMessage) {
    super(errorMessage);
  }
}
