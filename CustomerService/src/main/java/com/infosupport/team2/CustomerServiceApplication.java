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

	@Bean
	CommandLineRunner onStartup(CustomerRepository customerRepository) {
		return (String... args) -> {
			customerRepository.deleteAll();

			Address customerAddress = Address.builder()
					.street("Kerkstraat")
					.houseNumber("91")
					.city("Amsterdam")
					.zip("1564FH")
					.build();

			Customer customer = Customer.builder()
					.id("1")
					.name("Jan Meesters")
					.phone("0612345678")
					.email("jan.meesters@gmail.com")
					.address(customerAddress)
					.build();
			customer.generateKey();

			customerRepository.save(customer);

			customerAddress = Address.builder()
					.street("Kamperzand")
					.houseNumber("184")
					.city("Huizen")
					.zip("1274HM")
					.build();

			customer = Customer.builder()
					.id("2")
					.name("Derya Jongerden")
					.phone("0674558235")
					.email("derya@gmail.com")
					.address(customerAddress)
					.build();
			customer.generateKey();

			customerRepository.save(customer);

			customerAddress = Address.builder()
					.street("Breudijk")
					.houseNumber("145")
					.city("Rotterdam")
					.zip("3079TA")
					.build();

			customer = Customer.builder()
					.id("3")
					.name("Pieter Visser")
					.phone("0683552964")
					.email("pieter@hotmail.com")
					.address(customerAddress)
					.build();
			customer.generateKey();

			customerRepository.save(customer);

			System.out.println("All customers added");
			customerRepository.findAll().forEach(System.out::println);
		};
	}
}
