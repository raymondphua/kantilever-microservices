package com.infosupport.team2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Raymond Phua on 17-1-2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

    private String address;
    private String city;
    private String zip;
}
