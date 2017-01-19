package com.infosupport.team2.repository;

import com.infosupport.team2.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * Created by Raymond Phua on 18-1-2017.
 */
public interface CustomCustomerRepository {

    List<Customer> filterCustomer(Map<String, String> filters);
}
