package com.cnd13natation.performances.common.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Performance {

    private LocalDate date;

    private String event;

    private String location;

    private SwimmingType type;

    private PoolSize swimmingPoolSize;

}