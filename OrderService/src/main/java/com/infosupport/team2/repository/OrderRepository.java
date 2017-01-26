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
    Order findById(String id);

    Order findByStatus(Status status);

    Order findTopByOrderByIdDesc();

    List<Order> findByOrderDateAfter(LocalDateTime date);

    int countByOrderDateAfter(LocalDateTime date);
}
