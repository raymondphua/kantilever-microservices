package com.infosupport.team2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String id;
    private Long supplierId;
    private String name;
    private String description;
    private String imgUrl;
    private double price;
    private String supplierProductId;
}
