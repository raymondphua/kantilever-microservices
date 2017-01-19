package com.infosupport.team2.repository;

import com.infosupport.team2.enums.Status;
import com.infosupport.team2.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        } else {
            return Criteria.where(property.toLowerCase()).in(Arrays.asList(values));
        }
    }

    private String getPropertyForParam(String param) {
        switch (param.toLowerCase()) {
            case "status":
                return "status";
            default:
                return param;
        }
    }
}
