package com.cnd13natation.utils;

import java.time.format.DateTimeFormatter;

public final class DateUtils {

  public static final String FR_DATE_FORMAT = "dd/MM/yyyy";

  public static final DateTimeFormatter FR_DATE_FORMATTER =
      DateTimeFormatter.ofPattern(FR_DATE_FORMAT);

  private DateUtils() {
    // Private constructor for static class
  }
}
