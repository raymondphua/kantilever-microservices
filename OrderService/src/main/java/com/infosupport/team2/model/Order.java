package com.infosupport.team2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.infosupport.team2.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {

    public Order(Long id, double shippingFee, double totalPrice, Customer customer, List<Product> orderedProducts, Address deliveryAddress, Address invoiceAddress, Status status, LocalDateTime orderDate){
        this.id = id;
        this.shippingFee = shippingFee;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.orderedProducts = orderedProducts;
        this.deliveryAddress = deliveryAddress;
        this.invoiceAddress = invoiceAddress;
        this.status = status;
        this.orderDate = orderDate;
        generateKey(Math.toIntExact(id));
    }

    @Id
    private Long id;
    private String orderKey;
    private double shippingFee;
    private double totalPrice;
    private Customer customer;
    private List<Product> orderedProducts;
    private Address deliveryAddress;
    private Address invoiceAddress;
    private Status status;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime orderDate;

    public void generateKey(int order){
        StringBuilder sb = new StringBuilder();
        sb.append("ord-");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        sb.append(orderDate.format(formatter));
        sb.append("-");
        sb.append(String.format("%09d", order));

        this.orderKey = sb.toString();
    }
}
