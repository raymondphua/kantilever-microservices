package com.infosupport.team2;

import com.infosupport.team2.config.CustomMongoDBConvertor;
import com.infosupport.team2.model.ClientDetail;
import com.infosupport.team2.model.User;
import com.infosupport.team2.service.UserService;
import com.infosupport.team2.service.security.ClientDetailService;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories("com.infosupport.team2.repository")
@ComponentScan
@ImportResource({"classpath*:spring-security-oauth2.xml"})
public class AuthServerMain {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerMain.class, args);
    }

    @Autowired
    private CustomMongoDBConvertor customMongoDBConvertor;
    @Autowired
    private MongoDbFactory mongoDbFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    User testUser;
    ClientDetail authClient;
    @Value("${app.client.id}")
    private String authClientId;
    @Value("${app.client.secret}")
    private String authClientSecret;

    @Bean
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(customMongoDBConvertor);
        return new CustomConversions(converterList);
    }

    @Bean
    public MappingMongoConverter mongoConverter() throws Exception {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mappingContext);
        mongoConverter.setCustomConversions(customConversions());
        return mongoConverter;
    }

    @Bean(autowire = Autowire.BY_NAME, name = "mongoTemplate")
    public MongoTemplate customMongoTemplate() {
        try {
            return new MongoTemplate(mongoDbFactory, mongoConverter()); // a mongotemplate with custom convertor
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Bean
    CommandLineRunner onStartup(UserService userService, ClientDetailService clientDetailService) {
        return (String... args) -> {
            userService.deleteAll();
            clientDetailService.deleteAll();

            testUser = new User();
            testUser.setEmail("pieter@hotmail.com");
            testUser.setPassword(passwordEncoder.encode("henkie"));
            testUser.setId("12");
            userService.save(testUser);

            User employee = new User();
            employee.setEmail("dennis@kantilever.nl");
            employee.setPassword(passwordEncoder.encode("denny1"));
            List<String> employeeRights = new ArrayList<String>();
            employeeRights.add("employee");
            employee.setRights(employeeRights);
            employee.setId("16");
            userService.save(employee);

            authClient = new ClientDetail();
            authClient.setId("18");
            authClient.setClientId(authClientId);
            authClient.setResourceIds(new HashSet<>(Arrays.asList("rest_api")));
            authClient.setClientSecret(passwordEncoder.encode(authClientSecret));
            authClient.setRefreshTokenValiditySeconds(4500);
            authClient.setAccessTokenValiditySeconds(4500);
            authClient.setAuthorities(new HashSet<>(Arrays.asList("trust", "write", "employee")));
            authClient.setAuthorizedGrantTypes(new HashSet<>(Arrays.asList("client_credentials", "authorization_code", "implicit", "password", "refresh_token")));
            authClient.setScope(new HashSet<>(Arrays.asList("trust", "read", "write")));
            authClient.setSecretRequired(true);

            clientDetailService.save(authClient);
            clientDetailService.listClientDetails().forEach(System.out::println);
            userService.findAll().forEach(System.out::println);
        };
    }
}
