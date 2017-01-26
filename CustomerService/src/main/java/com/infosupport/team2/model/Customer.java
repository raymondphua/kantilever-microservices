package com.infosupport.team2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Raymond Phua on 17-1-2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class Customer {

    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private Address address;

    @Transient
    @JsonIgnore
    private String password;

    private String customerKey;

    public void generateKey() {
        StringBuilder sb = new StringBuilder();
        sb.append("cst-");
        String[] names = name.split(" ");
        sb.append(names[names.length - 1]);
        sb.append(name.charAt(0));
        sb.append("-");
        sb.append(address.getZip());

        this.customerKey = sb.toString();
    }
}
