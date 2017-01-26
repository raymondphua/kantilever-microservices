package com.infosupport.team2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String customerKey;
}
