package com.infosupport.team2.model;

import com.infosupport.team2.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProcessModel {

    private Status status;
}
