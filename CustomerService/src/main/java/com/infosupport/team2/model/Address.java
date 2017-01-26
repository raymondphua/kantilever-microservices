package com.infosupport.team2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @NotNull
    private String street;

    @NotNull
    private String houseNumber;

    @NotNull
    private String city;

    @NotNull
    private String zip;
}
