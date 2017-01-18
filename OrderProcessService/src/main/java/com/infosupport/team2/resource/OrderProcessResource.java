package com.infosupport.team2.resource;

import com.infosupport.team2.enums.Status;
import com.infosupport.team2.model.Order;
import com.infosupport.team2.model.OrderProcessModel;
import com.infosupport.team2.serviceCaller.OrderServiceCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/orders")
public class OrderProcessResource {

    @Autowired
    OrderServiceCaller orderServiceCaller;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return orderServiceCaller.createOrder(order);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Order> updateOrderStatus(@PathVariable String id, @RequestBody OrderProcessModel processModel) {
        Status status = Status.valueOf(processModel.getStatus().toUpperCase());
        return orderServiceCaller.updateOrderStatus(id, status);
    }
}
