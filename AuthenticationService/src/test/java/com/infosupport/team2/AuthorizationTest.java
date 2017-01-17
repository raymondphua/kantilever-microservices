package com.infosupport.team2;

import com.infosupport.team2.model.ClientDetail;
import com.infosupport.team2.model.User;
import com.infosupport.team2.service.UserService;
import com.infosupport.team2.service.security.ClientDetailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by Robin on 17-1-2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuthServerMain.class)
@WebAppConfiguration
@IntegrationTest
public class AuthorizationTest {
    @Autowired
    private UserService userService;
    @Autowired
    private ClientDetailService clientDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    User testUser;
    ClientDetail authClient;
    @Value("${app.client.id}")
    private String authClientId;
    @Value("${app.client.secret}")
    private String authClientSecret;


    @Before
    public void setUp() {

        userService.deleteAll();
        clientDetailService.deleteAll();

        testUser = new User();

        testUser.setEmail("pieter@hotmail.com");
        testUser.setPassword(passwordEncoder.encode("henkie"));
        testUser.setId("12");
        userService.save(testUser);

        authClient = new ClientDetail();
        authClient.setId("18");
        authClient.setClientId(authClientId);
        authClient.setResourceIds(new HashSet<>(Arrays.asList("rest_api")));
        authClient.setClientSecret(passwordEncoder.encode(authClientSecret));
        authClient.setRefreshTokenValiditySeconds(4500);
        authClient.setAccessTokenValiditySeconds(4500);
        authClient.setAuthorities(new HashSet<>(Arrays.asList("trust", "write")));
        authClient.setAuthorizedGrantTypes(new HashSet<>(Arrays.asList("client_credentials", "authorization_code", "implicit", "password", "refresh_token")));
        authClient.setScope(new HashSet<>(Arrays.asList("trust", "read", "write")));
        authClient.setSecretRequired(true);

        clientDetailService.save(authClient);

    }

    @Test
    public void testForInsert() {
        assertNotEquals("peter", "PETER");
    }
}
