package com.infosupport.team2.resource;

import com.infosupport.team2.model.Customer;
import com.infosupport.team2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Raymond Phua on 17-1-2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

    @Autowired
    private CustomerRepository customerRepo;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers(@RequestParam Map<String,String> allRequestParams) {
        return customerRepo.filterCustomer(allRequestParams);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable String id) {
        return customerRepo.findOne(id);
    }
}
