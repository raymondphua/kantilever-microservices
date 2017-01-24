package com.infosupport.team2.repository;

import com.infosupport.team2.enums.Status;
import com.infosupport.team2.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
public class OrderRepositoryImpl implements CustomOrderRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Order> filterOrders(Map<String, String> filters) {
        Query orderFilter = new Query();

        filters.forEach((k, v) -> {
            String property = getPropertyForParam(k);
            if (property != null) {
                orderFilter.addCriteria(getCriteriaForParam(property, v.split(",")));
            }
        });

        return mongoTemplate.find(orderFilter, Order.class);
    }

    @Override
    public List<Status> getAllStatuses() {
        return new ArrayList<>(Arrays.asList(Status.values()));
    }

    private Criteria getCriteriaForParam(String property, String[] values) {
        if (property.equals("status") && values.length > 0) {
            return Criteria.where(property).is(Status.valueOf(values[0].toUpperCase()));
        } else if (property.equals("orderDate") && values.length > 0) {
            Date createDate = new Date(Long.valueOf(values[0]));
            Instant instant = Instant.ofEpochMilli(createDate.getTime());
            LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            return Criteria.where(property).gte(date);
        } else {
            return Criteria.where(property.toLowerCase()).in(Arrays.asList(values));
        }
    }

    private String getPropertyForParam(String param) {
        switch (param.toLowerCase()) {
            case "status":
                return "status";
            case "dateafter":
                return "orderDate";
            default:
                return param;
        }
    }
}
