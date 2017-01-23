package com.infosupport.team2;

import com.infosupport.team2.model.Address;
import com.infosupport.team2.model.Customer;
import com.infosupport.team2.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

//	@Bean
//	CommandLineRunner onStartup(CustomerRepository customerRepository) {
//		return (String... args) -> {
//			Customer customer1 = new Customer();
//			customer1.setId("1");
//			customer1.setName("Jan Meesters");
//			customer1.setPhone("0612345678");
//			customer1.setEmail("jan.meesters@gmail.com");
//
//			Address address1 = new Address();
//			address1.setAddress("Hogevaart 91");
//			address1.setCity("Sprang-Capelle");
//			address1.setZip("5161PM");
//
//			Customer customer2 = new Customer();
//			customer2.setId("2");
//			customer2.setName("Pieter Doe");
//			customer2.setPhone("0687654321");
//			customer2.setEmail("pieter@hotmail.com");
//			customer2.setAddress(address1);
//
//			customerRepository.save(customer1);
//			customerRepository.save(customer2);
//
//			System.out.println("All customers added");
//			customerRepository.findAll().forEach(System.out::println);
//		};
//	}
}
