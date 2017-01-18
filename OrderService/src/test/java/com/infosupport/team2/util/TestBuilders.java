package com.infosupport.team2.util;

import com.infosupport.team2.model.Order;
import com.infosupport.team2.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymond Phua on 17-1-2017.
 */
public class TestBuilders {

    public static Order order() {
        List<Product> orderedProducts = new ArrayList<>();
        orderedProducts.add(Product.builder().price(5).quantity(1).build());
        orderedProducts.add(Product.builder().price(10).quantity(5).build());

        return Order.builder()
                .shippingFee(0)
                .orderedProducts(orderedProducts)
                .build();
    }
}
