package com.infosupport.team2.resource;

import com.infosupport.team2.model.User;
import com.infosupport.team2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by djones on 1/24/17.
 */
@RestController
@RequestMapping
public class CustomerResource {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/oauth/registration", method = RequestMethod.POST)
    public void createCustomer(@RequestBody User user) {
        userService.save(user);

    }
}
