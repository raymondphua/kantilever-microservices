package com.infosupport.team2.controller;

import com.infosupport.team2.model.Address;
import com.infosupport.team2.model.Customer;
import com.infosupport.team2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by djones on 1/23/17.
 */

@RestController
public class RegistrationController {

    @Autowired
    private CustomerRepository customerRepo;

    @RequestMapping(value = "/customer/registration", method = RequestMethod.POST)
    public String createCustomer(@ModelAttribute("userForm") Customer userFormCustomer, Address userFormAddress) {

        userFormCustomer.setAddress(userFormAddress);
        userFormCustomer.setEmail(userFormCustomer.getEmail().toLowerCase());
        customerRepo.save(userFormCustomer);

        return "Created";
    }

}
