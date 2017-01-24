package com.infosupport.team2.serviceCaller;

import com.infosupport.team2.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by djones on 1/24/17.
 */
@Service
public class AuthServiceCaller {

    private static final String AUTH_URL = "http://localhost:11150/oauth/registration";

    private RestTemplate restTemplate;

    public AuthServiceCaller() {
        restTemplate = new RestTemplate();
    }

    public Customer createUser(Customer customer) {

        return restTemplate.postForObject(AUTH_URL, customer, Customer.class);
    }
}
