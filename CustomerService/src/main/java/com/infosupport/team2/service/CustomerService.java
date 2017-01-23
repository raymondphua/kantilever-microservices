package com.infosupport.team2.service;

import com.infosupport.team2.model.Customer;
import com.infosupport.team2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Raymond Phua on 23-1-2017.
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    public List<Customer> getAllCustomers(Map<String, String> filters) {
        return customerRepo.filterCustomer(filters);
    }

    public Customer findById(String id) {
        return customerRepo.findOne(id);
    }

    public boolean emailExists(String email) {
        return customerRepo.findCustomerByEmail(email.toLowerCase()) != null;
    }
}
