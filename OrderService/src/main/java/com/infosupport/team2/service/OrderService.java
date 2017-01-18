package com.infosupport.team2.service;

import com.infosupport.team2.enums.Status;
import com.infosupport.team2.model.Order;
import com.infosupport.team2.model.ProcessOrderModel;
import com.infosupport.team2.model.Product;
import com.infosupport.team2.repository.OrderRepository;
import com.infosupport.team2.serviceCaller.OrderServiceCaller;
import com.infosupport.team2.util.PriceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Raymond Phua on 17-1-2017.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderServiceCaller orderServiceCaller;

    public Order createOrder(Order order) {

        //service caller, get all products and customer
        Order validatedOrder = orderServiceCaller.createOrder(order);

        //after validation from services calc total price + set status
        double totalPrice = PriceValidator.calcTotalPrice(order);
        validatedOrder.setTotalPrice(totalPrice);
        validatedOrder.setStatus(Status.BESTELD);

        //save in repo
        Order result = orderRepo.save(validatedOrder);

        return result;
    }

    public Order updateOrderStatus(String id, ProcessOrderModel processOrderModel) {

        Order foundOrder = orderRepo.findOne(id);
        foundOrder.setStatus(Status.valueOf(processOrderModel.getStatus().toUpperCase()));

        Order result = orderRepo.save(foundOrder);

        return result;
    }

    public List<Order> getOrders(Map<String, String> allRequestParams) {
        return orderRepo.filterOrders(allRequestParams);
    }

    public Order findOne(String id) {
        return orderRepo.findOne(id);
    }

    public List<Product> getProductsFromOrderId(String id) {
        return orderRepo.findOne(id).getOrderedProducts();
    }

    public String incrementId() {
        Order latestOrder = orderRepo.findTopByOrderByIdDesc();

        latestOrder = null;

        return "";
    }
}
