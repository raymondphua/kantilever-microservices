package com.infosupport.team2.resource;

import com.infosupport.team2.model.Customer;
import com.infosupport.team2.service.CustomerService;
import com.infosupport.team2.serviceCaller.AuthServiceCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Created by Raymond Phua on 17-1-2017.
 */
@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

    private static final String REDIRECT_URL = "http://localhost:3000/login";

    @Autowired
    CustomerService customerService;

    @Autowired
    private AuthServiceCaller authServiceCaller;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers(@RequestParam Map<String,String> allRequestParams) {
        return customerService.getAllCustomers(allRequestParams);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable String id) {
        return customerService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer result = customerService.createCustomer(customer);

        URI location = URI.create(ServletUriComponentsBuilder
                 .fromHttpUrl(REDIRECT_URL).toUriString());
        return ResponseEntity.created(location).build();
    }
}
