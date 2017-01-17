package com.infosupport.team2.model;

import com.infosupport.team2.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    private double shippingFee;
    private double totalPrice;
    private Customer customer;
    private List<Product> orderedProducts;
    private Address deliveryAddress;
    private Address invoiceAddress;
    private Status status;
}
