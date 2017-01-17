package com.infosupport.team2.service;

import com.infosupport.team2.model.Order;
import com.infosupport.team2.model.Product;

/**
 * Created by Raymond Phua on 17-1-2017.
 */
public class OrderProcessService {

    public static double calcTotalPrice(Order order) {
        double totalPrice = 0;

        for(Product p : order.getOrderedProducts()) {
            totalPrice += p.getPrice() * p.getQuantity();
        }

        totalPrice += order.getShippingFee();

        return totalPrice;
    }
}
