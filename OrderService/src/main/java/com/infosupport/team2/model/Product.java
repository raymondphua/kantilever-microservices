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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String id;
    private String productKey;
    private Long supplierId;
    private String name;
    private String description;
    private String imgUrl;
    private double price;
    private String supplierProductId;
    private int quantity;
}
