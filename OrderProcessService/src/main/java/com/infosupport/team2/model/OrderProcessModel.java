package com.infosupport.team2.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Raymond Phua on 18-1-2017.
 */
@Data
@NoArgsConstructor
public class OrderProcessModel implements Serializable {

    private String status;
}
