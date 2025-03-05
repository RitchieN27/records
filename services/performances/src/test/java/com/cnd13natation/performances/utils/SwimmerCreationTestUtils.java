package com.cnd13natation.performances.utils;

import com.cnd13natation.performances.batch.common.SwimmerCSV;
import com.cnd13natation.performances.common.domain.Performance;
import com.cnd13natation.performances.common.domain.PoolSize;
import com.cnd13natation.performances.common.domain.SwimmingType;
import com.cnd13natation.performances.swimmer.domain.Swimmer;
import java.time.Duration;
import java.time.LocalDate;
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
    final var firstSwimmer =
        SwimmerCSV.builder()
            .federalIdentifier("976587")
            .name("LENCLEN")
            .firstName("Clara")
            .swimmingType(SwimmingType.WOMEN_FREESTYLE_50)
            .poolSize(PoolSize.TWENTY_FIVE)
            .time(Duration.ofSeconds(30).plusMillis(2))
            .date(LocalDate.of(2018, 11, 10))
            .age(23)
            .event("Interclubs Toutes Catégories - Poule A")
            .location("PARIS")
            .build();
    swimmerCSVList.add(firstSwimmer);
    final var secondSwimmer =
        SwimmerCSV.builder()
            .federalIdentifier("2681823")
            .name("FEIGE")
            .firstName("Ludovic")
            .swimmingType(SwimmingType.MEN_BREASTSTROKE_100)
            .poolSize(PoolSize.TWENTY_FIVE)
            .time(Duration.ofMinutes(1).plusSeconds(20).plusMillis(33))
            .date(LocalDate.of(2018, 11, 11))
            .age(26)
            .event("Interclubs Toutes Catégories - Poule A")
            .location("PARIS")
            .build();
    swimmerCSVList.add(secondSwimmer);
    final var thirdSwimmer =
        SwimmerCSV.builder()
            .federalIdentifier("1338708")
            .name("BORDIER")
            .firstName("Raphaelle")
            .swimmingType(SwimmingType.WOMEN_FREESTYLE_50)
            .poolSize(PoolSize.TWENTY_FIVE)
            .time(Duration.ofSeconds(32).plusMillis(26))
            .date(LocalDate.of(2024, 11, 16))
            .age(32)
            .event("Championnats Régionaux Interclubs TC - Poule A")
            .location("PARIS")
            .build();
    swimmerCSVList.add(thirdSwimmer);
    return swimmerCSVList;
  }

  public static List<Swimmer> createSwimmers() {
    final var createdSwimmers = new ArrayList<Swimmer>();
    final var firstCreatedSwimmer =
        Swimmer.builder().federalIdentifier("976587").name("LENCLEN").firstName("Clara").build();
    final var firstSwimmerPerformance =
        Performance.builder()
            .date(LocalDate.of(2018, 11, 10))
            .event("Interclubs Toutes Catégories - Poule A")
            .location("PARIS")
            .type(SwimmingType.WOMEN_FREESTYLE_50)
            .time(Duration.ofSeconds(30).plusMillis(2))
            .swimmingPoolSize(PoolSize.TWENTY_FIVE)
            .build();
    firstCreatedSwimmer.getPerformances().add(firstSwimmerPerformance);
    createdSwimmers.add(firstCreatedSwimmer);
    final var secondCreatedSwimmer =
        Swimmer.builder().federalIdentifier("2681823").name("FEIGE").firstName("Ludovic").build();
    final var secondSwimmerPerformance =
        Performance.builder()
            .date(LocalDate.of(2018, 11, 11))
            .event("Interclubs Toutes Catégories - Poule A")
            .location("PARIS")
            .type(SwimmingType.MEN_BREASTSTROKE_100)
            .time(Duration.ofMinutes(1).plusSeconds(20).plusMillis(33))
            .swimmingPoolSize(PoolSize.TWENTY_FIVE)
            .build();
    secondCreatedSwimmer.getPerformances().add(secondSwimmerPerformance);
    createdSwimmers.add(secondCreatedSwimmer);
    final var thirdCreatedSwimmer =
        Swimmer.builder()
            .federalIdentifier("1338708")
            .name("BORDIER")
            .firstName("Raphaelle")
            .build();
    final var thirdSwimmerPerformance =
        Performance.builder()
            .date(LocalDate.of(2018, 11, 16))
            .event("Championnats Régionaux Interclubs TC - Poule A")
            .location("PARIS")
            .type(SwimmingType.WOMEN_FREESTYLE_50)
            .time(Duration.ofSeconds(32).plusMillis(26))
            .swimmingPoolSize(PoolSize.TWENTY_FIVE)
            .build();
    thirdCreatedSwimmer.getPerformances().add(thirdSwimmerPerformance);
    createdSwimmers.add(thirdCreatedSwimmer);
    return createdSwimmers;
  }
}
