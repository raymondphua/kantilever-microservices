package com.infosupport.team2.resource;

import com.infosupport.team2.model.Customer;
import com.infosupport.team2.repository.CustomerRepository;
import com.infosupport.team2.service.CustomerService;
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
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers(@RequestParam Map<String,String> allRequestParams) {
        return customerService.getAllCustomers(allRequestParams);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable String id) {
        return customerService.findById(id);
    }

}
