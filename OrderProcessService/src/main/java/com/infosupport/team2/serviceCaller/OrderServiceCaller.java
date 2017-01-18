package com.infosupport.team2.serviceCaller;

import com.infosupport.team2.enums.Status;
import com.infosupport.team2.model.Order;
import com.infosupport.team2.model.Product;
import com.infosupport.team2.service.OrderProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymond Phua on 17-1-2017.
 */
@Service
public class OrderServiceCaller {

    private static final String BASE_URL = "http://localhost:11130/orderservice/orders";
    private static final String PRODUCT_URL = "http://localhost:11130/catalog/products";
    private static final String CUSTOMER_URL = "http://localhost:11130/customerservice/customers";

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<Order> createOrder(Order order) {
        List<Product> products = new ArrayList<>();
        order.getOrderedProducts().forEach(p -> {
            Product found = restTemplate.getForObject(PRODUCT_URL + "/{id}", Product.class, p.getId());
            found.setQuantity(p.getQuantity());
            products.add(found);
        });
        order.setOrderedProducts(products);

        double totalPrice = OrderProcessService.calcTotalPrice(order);
        order.setTotalPrice(totalPrice);

        Order result = restTemplate.postForObject(BASE_URL, order, Order.class);
        URI location = ServletUriComponentsBuilder
                .fromHttpUrl(BASE_URL)
                .path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Order> updateOrderStatus(String id, Status status) {
        restTemplate.put(BASE_URL + "/{id}", status, id);
        URI location = ServletUriComponentsBuilder
                .fromHttpUrl(BASE_URL)
                .path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.noContent().location(location).build();
    }
}

