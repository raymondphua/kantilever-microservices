package com.infosupport.team2.repository;

import com.infosupport.team2.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

/**
 * Created by Robin on 17-1-2017.
 */
public interface UserRepository extends MongoRepository<User, Serializable> {

    public User findByEmail(String email);
}