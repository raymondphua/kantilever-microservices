package com.infosupport.team2;

import com.infosupport.team2.enums.Status;
import com.infosupport.team2.model.Address;
import com.infosupport.team2.model.Customer;
import com.infosupport.team2.model.Order;
import com.infosupport.team2.model.Product;
import com.infosupport.team2.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
public class OrderServiceApplication extends ResourceServerConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner onStartup(OrderRepository orderRepository) {
		return (String... args) -> {
			orderRepository.deleteAll();

			Customer customer1 = new Customer();
			customer1.setId("1");
			customer1.setName("Jan Meesters");
			customer1.setPhone("0612345678");
			customer1.setEmail("jan.meesters@gmail.com");
			customer1.setAddress(new Address("Test straat 13", "Breda", "1234AB"));

			Customer customer2 = new Customer();
			customer2.setId("2");
			customer2.setName("Piet Piraat");
			customer2.setPhone("0687654321");
			customer2.setEmail("piet.piraat@gmail.com");
			customer2.setAddress(new Address("Piratenstraat 9", "Groningen", "1122AA"));

			List<Product> orderedProducts1 = new ArrayList<>();
			orderedProducts1.add(new Product("1", 2L, "Road-150 Red, 62", "This bike is ridden by race winners. Developed with the Adventure Works Cycles professional race team, it has a extremely light heat-treated aluminum frame, and steering that allows precision control.", "http://res.cloudinary.com/kantilever/image/upload/v1484171992/bike3_vhdpgz.png", 1366, "LJ-0192-S", 1));
			orderedProducts1.add(new Product("2", 8L, "HL Road Frame - Black, 58", "Our lightest and best quality aluminum frame made from the newest alloy; it is welded and heat-treated for strength. Our innovative design results in maximum comfort and performance.", "http://res.cloudinary.com/kantilever/image/upload/v1484171995/frame3_iz3yj5.jpg", 1364.50, "FR-M94S-42", 3));
			orderedProducts1.add(new Product("3", 2L, "Road-150 Frame Red, 62", "Our lightest and best quality aluminum frame made from the newest alloy; it is welded and heat-treated for strength. Our innovative design results in maximum comfort and performance.", "http://res.cloudinary.com/kantilever/image/upload/v1484171995/frame2_gpedbr.jpg", 1349.60, "FR-M94S-42", 1));

			List<Product> orderedProducts2 = new ArrayList<>();
			orderedProducts2.add(new Product("1", 2L, "Road-150 Red, 62", "This bike is ridden by race winners. Developed with the Adventure Works Cycles professional race team, it has a extremely light heat-treated aluminum frame, and steering that allows precision control.", "http://res.cloudinary.com/kantilever/image/upload/v1484171992/bike3_vhdpgz.png", 1366, "LJ-0192-S", 1));

			Address afleverAdres1 = new Address("Leerpark 120", "Dordrecht", "1111AA");

			orderRepository.save(new Order("1", 3.50, 100, customer1, orderedProducts1, customer1.getAddress(), Status.BESTELD));
			orderRepository.save(new Order("2", 3.50, 200, customer1, orderedProducts1, afleverAdres1, Status.AFGELEVERD));
			orderRepository.save(new Order("3", 5, 70, customer2, orderedProducts2, customer2.getAddress(), Status.BESTELD));
			orderRepository.save(new Order("4", 7.50, 125, customer2, orderedProducts2, afleverAdres1, Status.AFGELEVERD));

			for (int i = 5; i < 10; i++) {
				orderRepository.save(new Order("" + i, 3.50, 524, customer1, orderedProducts1, customer1.getAddress(), Status.AFGELEVERD));
			}

			System.out.println("All orders added");
			orderRepository.findAll().forEach(System.out::println);
		};
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/oauth/**", "/oauth").permitAll().anyRequest().authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
