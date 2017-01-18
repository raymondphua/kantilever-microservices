package com.infosupport.team2.serviceCaller;

import com.infosupport.team2.model.Order;
import com.infosupport.team2.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymond Phua on 17-1-2017.
 */
@Service
public class OrderServiceCaller {

    private static final String PRODUCT_URL = "http://catalog-service/products";
    private static final String CUSTOMER_URL = "http://localhost:11130/customerservice/customers";

    @Autowired
    RestTemplate restTemplate;

    public Order createOrder(Order order) {
        List<Product> products = new ArrayList<>();
        order.getOrderedProducts().forEach(p -> {
            Product found = restTemplate.getForObject(PRODUCT_URL + "/{id}", Product.class, p.getId());
            found.setQuantity(p.getQuantity());
            products.add(found);
        });
        order.setOrderedProducts(products);

        return order;
    }
}

