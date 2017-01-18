package com.infosupport.team2.model;

import com.infosupport.team2.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Raymond Phua on 17-1-2017.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private String id;
    private double shippingFee;
    private double totalPrice;
    private Customer customer;
    private List<Product> orderedProducts;
    private Address deliveryAddress;
    private Address invoiceAddress;
    private Status status;
}
