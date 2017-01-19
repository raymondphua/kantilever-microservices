package com.infosupport.team2.util;

import com.infosupport.team2.model.Order;
import com.infosupport.team2.model.Product;

/**
 * Created by Raymond Phua on 18-1-2017.
 */
public class PriceValidator {

    public static double calcTotalPrice(Order order) {
        double totalPrice = 0;

        for(Product p : order.getOrderedProducts()) {
            totalPrice += p.getPrice() * p.getQuantity();
        }

        totalPrice += order.getShippingFee();

        return totalPrice;
    }
}