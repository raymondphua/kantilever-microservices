package com.infosupport.team2.resource;

import com.infosupport.team2.enums.Status;
import com.infosupport.team2.model.Order;
import com.infosupport.team2.model.OrderProcessModel;
import com.infosupport.team2.model.Product;
import com.infosupport.team2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/orders")
public class OrderProcessResource {

    private static final String ORDER_URL = "http://localhost:11130/orderservice/orders";
    @Autowired
    private OrderRepository orderRepo;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        double totalPrice = calcPrice(order);
        order.setStatus(Status.BESTELD);
        order.setTotalPrice(totalPrice);

        Order result = orderRepo.save(order);
        URI location = ServletUriComponentsBuilder
                .fromHttpUrl(ORDER_URL)
                .path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value="/{orderId}", method = RequestMethod.PUT)
    public ResponseEntity<Order> updateOrderStatus(@PathVariable String orderId, @RequestBody OrderProcessModel orderProcessModel) {
        Order found = orderRepo.findById(orderId);
        found.setStatus(orderProcessModel.getStatus());

        Order result = orderRepo.save(found);

        URI location = ServletUriComponentsBuilder
                .fromHttpUrl(ORDER_URL)
                .path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.noContent().location(location).build();
    }

    private double calcPrice(Order order) {
        double totalPrice = 0;

        for(Product p : order.getOrderedProducts()) {
            totalPrice += p.getPrice() * p.getQuantity();
        }

        totalPrice += order.getShippingFee();

        return totalPrice;
    }
}
