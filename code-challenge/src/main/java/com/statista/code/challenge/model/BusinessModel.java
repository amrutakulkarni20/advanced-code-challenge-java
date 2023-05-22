package com.statista.code.challenge.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BusinessModel {

    private Departments department;

    private String region;

    private String businessPlan;

    private String email;
}
