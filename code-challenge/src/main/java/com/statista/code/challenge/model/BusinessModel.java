package com.statista.code.challenge.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BusinessModel {

    @NotNull(message = "Department cannot be left blank.")
    private Departments department;

    @NotNull(message = "Region cannot be left blank.")
    private String region;

    @NotNull(message = "Business Plan cannot be left blank.")
    private String businessPlan;

    @NotNull(message = "Email cannot be left blank.")
    private String email;
}
