package com.infosupport.team2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Raymond Phua on 23-1-2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationModel {

    private String email;
    private boolean valid;
}
