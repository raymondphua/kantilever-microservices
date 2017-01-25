package com.infosupport.team2.resource;

import com.infosupport.team2.enums.Status;
import com.infosupport.team2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Raymond Phua on 16-1-2017.
 */
@RestController
@RequestMapping(value = {"/statuses/", "/statuses"})
public class StatusResource {

    @Autowired
    private OrderRepository orderRepo;

    @PreAuthorize("hasAuthority('employee')")
    @RequestMapping(method = RequestMethod.GET)
    public List<Status> allStatuses()
    {
        return orderRepo.getAllStatuses();
    }
}
