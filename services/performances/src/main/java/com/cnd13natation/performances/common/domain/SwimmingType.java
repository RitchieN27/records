package com.cnd13natation.performances.common.domain;

public enum SwimmingType {
    FREESTYLE_25("25 Nage Libre"),
    FREESTYLE_50("50 Nage Libre"),
    FREESTYLE_100("100 Nage Libre"),
    FREESTYLE_200("200 Nage Libre"),
    FREESTYLE_400("400 Nage Libre"),
    FREESTYLE_600("600 Nage Libre"),
    FREESTYLE_800("800 Nage Libre"),
    FREESTYLE_1500("1500 Nage Libre"),
    BREASTSTROKE_25("25 Brasse"),
    BREASTSTROKE_50("50 Brasse"),
    BREASTSTROKE_100("100 Brasse"),
    BREASTSTROKE_200("200 Brasse"),
    BACKSTROKE_25("25 Brasse"),
    BACKSTROKE_50("50 Dos"),
    BACKSTROKE_100("100 Dos"),
    BACKSTROKE_200("200 Dos"),
    BUTTERFLY_25("25 Papillon"),
    BUTTERFLY_50("50 Papillon"),
    BUTTERFLY_100("100 Papillon"),
    BUTTERFLY_200("200 Papillon"),
    MEDLEY_100("100 4 Nages"),
    MEDLEY_200("200 4 Nages"),
    MEDLEY_300("300 4 Nages"),
    MEDLEY_400("400 4 Nages"),
    MEDLEY_800("800 4 Nages");

    public final String label;

    private SwimmingType(String label) {
        this.label = label;
    }

}