package com.infosupport.team2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    private String address;
    private String city;
    private String zip;
}
