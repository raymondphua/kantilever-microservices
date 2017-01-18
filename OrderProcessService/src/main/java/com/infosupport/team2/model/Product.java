package com.infosupport.team2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Raymond Phua on 17-1-2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Serializable{

    private String id;
    private Long supplierId;
    private String name;
    private String description;
    private String imgUrl;
    private double price;
    private String supplierProductId;
    private int quantity;
}