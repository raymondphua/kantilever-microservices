package com.infosupport.team2.repository;

import com.infosupport.team2.model.OAuth2AuthenticationRefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Robin on 17-1-2017.
 */
public interface OAuth2RefreshTokenRepository extends MongoRepository<OAuth2AuthenticationRefreshToken, String> {


}
