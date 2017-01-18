package com.infosupport.team2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Raymond Phua on 17-1-2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Customer {

    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
}
