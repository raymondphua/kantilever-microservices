package com.infosupport.team2.repository;

import com.infosupport.team2.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Raymond Phua on 17-1-2017.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
