package com.infosupport.team2.repository;

import com.infosupport.team2.enums.Status;
import com.infosupport.team2.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
public interface OrderRepository extends MongoRepository<Order, Long>, CustomOrderRepository {
    public Order findById(String id);
    public Order findByStatus(Status status);
    public Order findTopByOrderByIdDesc();
    public List<Order> findByOrderDateAfter(LocalDateTime date);
    public int countByOrderDateAfter(LocalDateTime date);
}
