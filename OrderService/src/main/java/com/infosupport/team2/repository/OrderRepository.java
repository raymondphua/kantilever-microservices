package com.infosupport.team2.repository;

import com.infosupport.team2.enums.Status;
import com.infosupport.team2.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
public interface OrderRepository extends MongoRepository<Order, String>, CustomOrderRepository {
    public Order findById(String id);
    public Order findByStatus(Status status);
    public Order findTopByOrderByIdDesc();
}
