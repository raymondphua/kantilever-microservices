package com.infosupport.team2.resource;

import com.infosupport.team2.enums.Status;
import com.infosupport.team2.model.Order;
import com.infosupport.team2.model.Product;
import com.infosupport.team2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderRepository orderRepo;

    @RequestMapping(method = RequestMethod.GET)
    public List<Order> allOrdersWithStatus(@RequestParam Map<String,String> allRequestParams) {
        return orderRepo.filterOrders(allRequestParams);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable String id) {
        return orderRepo.findOne(id);
    }

    @RequestMapping(value = "/{id}/products", method = RequestMethod.GET)
    public List<Product> getProductsFromOrder(@PathVariable String id) {
        return orderRepo.findOne(id).getOrderedProducts();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Order createOrder(@RequestBody Order order) {
        order.setStatus(Status.BESTELD);
        return orderRepo.save(order);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Order updateStatusFromOrder(@PathVariable String id, @RequestBody Status status) {
        Order foundOrder = orderRepo.findOne(id);
        foundOrder.setStatus(status);

        return orderRepo.save(foundOrder);
    }
}
