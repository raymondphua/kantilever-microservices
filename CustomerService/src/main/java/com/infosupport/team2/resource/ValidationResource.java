package com.infosupport.team2.resource;

import com.infosupport.team2.model.ValidationModel;
import com.infosupport.team2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Raymond Phua on 23-1-2017.
 */
@CrossOrigin
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
