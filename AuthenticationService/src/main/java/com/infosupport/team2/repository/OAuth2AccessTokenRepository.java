package com.infosupport.team2.repository;

import com.infosupport.team2.model.OAuth2AuthenticationAccessToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

/**
 * Created by Robin on 17-1-2017.
 */
public interface OAuth2AccessTokenRepository extends MongoRepository<OAuth2AuthenticationAccessToken, Serializable> {

}

