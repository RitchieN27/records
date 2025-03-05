package com.cnd13natation.performances.common.domain;

import java.util.Optional;

/**
 * Enumeration for the swimming type
 *
 * @author Ritchie Nithoo
 */
public enum SwimmingType {
  // MEN SWIMMING TYPE
  MEN_FREESTYLE_25("25 Nage Libre Messieurs"),
  MEN_FREESTYLE_50("50 Nage Libre Messieurs"),
  MEN_FREESTYLE_100("100 Nage Libre Messieurs"),
  MEN_FREESTYLE_200("200 Nage Libre Messieurs"),
  MEN_FREESTYLE_400("400 Nage Libre Messieurs"),
  MEN_FREESTYLE_600("600 Nage Libre Messieurs"),
  MEN_FREESTYLE_800("800 Nage Libre Messieurs"),
  MEN_FREESTYLE_1500("1500 Nage Libre Messieurs"),
  MEN_BREASTSTROKE_25("25 Brasse Messieurs"),
  MEN_BREASTSTROKE_50("50 Brasse Messieurs"),
  MEN_BREASTSTROKE_100("100 Brasse Messieurs"),
  MEN_BREASTSTROKE_200("200 Brasse Messieurs"),
  MEN_BACKSTROKE_25("25 Brasse Messieurs"),
  MEN_BACKSTROKE_50("50 Dos Messieurs"),
  MEN_BACKSTROKE_100("100 Dos Messieurs"),
  MEN_BACKSTROKE_200("200 Dos Messieurs"),
  MEN_BUTTERFLY_25("25 Papillon Messieurs"),
  MEN_BUTTERFLY_50("50 Papillon Messieurs"),
  MEN_BUTTERFLY_100("100 Papillon Messieurs"),
  MEN_BUTTERFLY_200("200 Papillon Messieurs"),
  MEN_MEDLEY_100("100 4 Nages Messieurs"),
  MEN_MEDLEY_200("200 4 Nages Messieurs"),
  MEN_MEDLEY_300("300 4 Nages Messieurs"),
  MEN_MEDLEY_400("400 4 Nages Messieurs"),
  MEN_MEDLEY_800("800 4 Nages Messieurs"),

  // WOMEN SWIMMING TYPE
  WOMEN_FREESTYLE_25("25 Nage Libre Dames"),
  WOMEN_FREESTYLE_50("50 Nage Libre Dames"),
  WOMEN_FREESTYLE_100("100 Nage Libre Dames"),
  WOMEN_FREESTYLE_200("200 Nage Libre Dames"),
  WOMEN_FREESTYLE_400("400 Nage Libre Dames"),
  WOMEN_FREESTYLE_600("600 Nage Libre Dames"),
  WOMEN_FREESTYLE_800("800 Nage Libre Dames"),
  WOMEN_FREESTYLE_1500("1500 Nage Libre Dames"),
  WOMEN_BREASTSTROKE_25("25 Brasse Dames"),
  WOMEN_BREASTSTROKE_50("50 Brasse Dames"),
  WOMEN_BREASTSTROKE_100("100 Brasse Dames"),
  WOMEN_BREASTSTROKE_200("200 Brasse Dames"),
  WOMEN_BACKSTROKE_25("25 Brasse Dames"),
  WOMEN_BACKSTROKE_50("50 Dos Dames"),
  WOMEN_BACKSTROKE_100("100 Dos Dames"),
  WOMEN_BACKSTROKE_200("200 Dos Dames"),
  WOMEN_BUTTERFLY_25("25 Papillon Dames"),
  WOMEN_BUTTERFLY_50("50 Papillon Dames"),
  WOMEN_BUTTERFLY_100("100 Papillon Dames"),
  WOMEN_BUTTERFLY_200("200 Papillon Dames"),
  WOMEN_MEDLEY_100("100 4 Nages Dames"),
  WOMEN_MEDLEY_200("200 4 Nages Dames"),
  WOMEN_MEDLEY_300("300 4 Nages Dames"),
  WOMEN_MEDLEY_400("400 4 Nages Dames"),
  WOMEN_MEDLEY_800("800 4 Nages Dames");

  public final String label;

  SwimmingType(String label) {
    this.label = label;
  }

  public static Optional<SwimmingType> fromLabel(String label) {
    for (SwimmingType swimmingType : SwimmingType.values()) {
      if (swimmingType.label.equals(label)) {
        return Optional.of(swimmingType);
      }
    }
    return Optional.empty();
  }
}
