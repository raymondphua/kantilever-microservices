package com.infosupport.team2.resource;

import com.infosupport.team2.model.ValidationModel;
import com.infosupport.team2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Raymond Phua on 23-1-2017.
 */
@RestController
@RequestMapping(value = "/customers/validate")
public class ValidationResource {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value="/email", method = RequestMethod.POST)
    public ValidationModel emailExists(@RequestBody ValidationModel validationModel) {
        validationModel.setValid(customerService.emailExists(validationModel.getEmail()));
        return validationModel;
    }
}
