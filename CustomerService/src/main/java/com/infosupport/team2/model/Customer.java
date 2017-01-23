package com.infosupport.team2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

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

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private Address address;

    @Transient
    @JsonIgnore
    private String password;
}
