package com.infosupport.team2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Raymond Phua on 17-1-2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

    private String id;
    private String name;
    private String email;
    private String phone;
}
