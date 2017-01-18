package com.infosupport.team2.repository;

import com.infosupport.team2.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Raymond Phua on 18-1-2017.
 */
public class CustomerRepositoryImpl implements CustomCustomerRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Customer> filterCustomer(Map<String, String> filters) {
        Query customerFilter = new Query();

        filters.forEach((k, v) -> {
            String property = getPropertyForParam(k);
            if (property != null) {
                customerFilter.addCriteria(getCriteriaForParam(property, v.split(",")));
            }
        });

        return mongoTemplate.find(customerFilter, Customer.class);
    }

    private Criteria getCriteriaForParam(String property, String[] values) {
        if (property.equals("email") && values.length > 0) {
            return Criteria.where(property).is(values[0]);
        } else {
            return Criteria.where(property.toLowerCase()).in(Arrays.asList(values));
        }
    }

    private String getPropertyForParam(String param) {
        switch (param.toLowerCase()) {
            case "email":
                return "email";
            default:
                return param;
        }
    }
}
