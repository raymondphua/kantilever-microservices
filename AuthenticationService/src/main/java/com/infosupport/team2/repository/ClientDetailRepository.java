package com.infosupport.team2.repository;

import com.infosupport.team2.model.ClientDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

/**
 * Created by Robin on 17-1-2017.
 */
public interface ClientDetailRepository extends MongoRepository<ClientDetail, Serializable> {

    public ClientDetail findByClientId(String clientId);

}
