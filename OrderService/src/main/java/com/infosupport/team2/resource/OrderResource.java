package com.infosupport.team2.resource;

import com.infosupport.team2.model.Order;
import com.infosupport.team2.model.ProcessOrderModel;
import com.infosupport.team2.model.Product;
import com.infosupport.team2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
@RestController
@RequestMapping(value = "/orders")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAuthority('employee')")
    @RequestMapping(method = RequestMethod.GET)
    public List<Order> allOrdersWithStatus(@RequestParam Map<String, String> allRequestParams) {
        return orderService.getOrders(allRequestParams);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable Long id) {
        return orderService.findOne(id);
    }


    @RequestMapping(value = "/{id}/products", method = RequestMethod.GET)
    public List<Product> getProductsFromOrder(@PathVariable Long id) {
        return orderService.getProductsFromOrderId(id);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order result = orderService.createOrder(order);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/orders/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Order> updateStatusFromOrder(@PathVariable Long id, @RequestBody ProcessOrderModel processOrderModel) {

        Order result = orderService.updateOrderStatus(id, processOrderModel);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/orders/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.noContent().location(location).build();
    }
}