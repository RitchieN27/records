package com.cnd13natation.performances.utils;

import com.cnd13natation.performances.batch.common.SwimmerCSV;
import java.util.ArrayList;
import java.util.List;

/**
 * Utils class to manipulate swimmer data for test purposes
 *
 * @author Ritchie Nithoo
 */
public class SwimmerCreationTestUtils {

  private SwimmerCreationTestUtils() {
    // Empty private constructor
  }

  public static List<SwimmerCSV> createSwimmerCSV() {
    List<SwimmerCSV> swimmerCSVList = new ArrayList<>();
    final var firstSwimmer = SwimmerCSV.builder().build();
    swimmerCSVList.add(firstSwimmer);
    final var secondSwimmer = SwimmerCSV.builder().build();
    swimmerCSVList.add(secondSwimmer);
    final var thirdSwimmer = SwimmerCSV.builder().build();
    swimmerCSVList.add(thirdSwimmer);
    return swimmerCSVList;
  }
}
