package com.infosupport.team2.repository;

import com.infosupport.team2.enums.Status;
import com.infosupport.team2.model.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
public interface CustomOrderRepository {

    public List<Order> filterOrders(Map<String, String> filters);
    public List<Status> getAllStatuses();
}
