package com.cnd13natation.performances.swimmer.domain;

import com.cnd13natation.performances.common.domain.Performance;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Swimmer {

    @Id
    private String id;

    private String federalIdentifier;

    private String name;

    private String firstName;

    private List<Performance> performances;

}