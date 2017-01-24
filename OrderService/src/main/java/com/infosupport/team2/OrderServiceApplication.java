package com.infosupport.team2;

import com.infosupport.team2.enums.Status;
import com.infosupport.team2.model.Address;
import com.infosupport.team2.model.Customer;
import com.infosupport.team2.model.Order;
import com.infosupport.team2.model.Product;
import com.infosupport.team2.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
@Configuration
@EnableAutoConfiguration
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	CommandLineRunner onStartup(OrderRepository orderRepository) {
		return (String... args) -> {
			orderRepository.deleteAll();

			Customer customer1 = Customer.builder()
					.id("1")
					.name("Jan Meesters")
					.phone("0612345678")
					.email("jan.meesters@gmail.com")
					.customerKey("cst-MeestersJ-1564FH")
					.build();

			Customer customer2 = Customer.builder()
					.id("2")
					.name("Derya Jongerden")
					.phone("0674558235")
					.email("derya@gmail.com")
					.customerKey("cst-JongerdenD-1274HM")
					.build();

			Customer customer3 = Customer.builder()
					.id("3")
					.name("Pieter Visser")
					.phone("0683552964")
					.email("pieter@hotmail.com")
					.customerKey("cst-VisserP-3079TA")
					.build();

			List<Product> orderedProducts1 = new ArrayList<>();
			orderedProducts1.add(new Product("1", "prd-BAT-LJ0192S", 2L, "Road-150 Red, 62", "This bike is ridden by race winners. Developed with the Adventure Works Cycles professional race team, it has a extremely light heat-treated aluminum frame, and steering that allows precision control.", "http://res.cloudinary.com/kantilever/image/upload/v1484171992/bike3_vhdpgz.png", 1366, "LJ-0192-S", 1));
			orderedProducts1.add(new Product("2", "prd-KOG-FRM94S42", 8L, "HL Road Frame - Black, 58", "Our lightest and best quality aluminum frame made from the newest alloy; it is welded and heat-treated for strength. Our innovative design results in maximum comfort and performance.", "http://res.cloudinary.com/kantilever/image/upload/v1484171995/frame3_iz3yj5.jpg", 1364.50, "FR-M94S-42", 3));
			orderedProducts1.add(new Product("3","prd-BAT-FRM94S42", 2L, "Road-150 Frame Red, 62", "Our lightest and best quality aluminum frame made from the newest alloy; it is welded and heat-treated for strength. Our innovative design results in maximum comfort and performance.", "http://res.cloudinary.com/kantilever/image/upload/v1484171995/frame2_gpedbr.jpg", 1349.60, "FR-M94S-42", 1));

			List<Product> orderedProducts2 = new ArrayList<>();
			orderedProducts2.add(new Product("1", "prd-BAT-LJ0192S", 2L, "Road-150 Red, 62", "This bike is ridden by race winners. Developed with the Adventure Works Cycles professional race team, it has a extremely light heat-treated aluminum frame, and steering that allows precision control.", "http://res.cloudinary.com/kantilever/image/upload/v1484171992/bike3_vhdpgz.png", 1366, "FG-485-S", 1));

			List<Product> orderedProducts3 = new ArrayList<>();
			orderedProducts2.add(new Product("1", "prd-BAT-LJ0192S", 2L, "Road-150 Red, 62", "This bike is ridden by race winners. Developed with the Adventure Works Cycles professional race team, it has a extremely light heat-treated aluminum frame, and steering that allows precision control.", "http://res.cloudinary.com/kantilever/image/upload/v1484171992/bike3_vhdpgz.png", 1366, "FG-485-S", 10));
			orderedProducts1.add(new Product("2", "prd-KOG-FRM94S42", 8L, "HL Road Frame - Black, 58", "Our lightest and best quality aluminum frame made from the newest alloy; it is welded and heat-treated for strength. Our innovative design results in maximum comfort and performance.", "http://res.cloudinary.com/kantilever/image/upload/v1484171995/frame3_iz3yj5.jpg", 1364.50, "FR-M94S-42", 3));
			Address afleverAdres1 = new Address("Leerpark 120", "Dordrecht", "1111AA");

			Address factuurAdres1 = new Address("Kruisboog 42","Veenendaal","4444ZZ");

			LocalDateTime date1 = LocalDateTime.now();
			LocalDateTime date2 = LocalDateTime.now().minusMinutes(30);
			LocalDateTime date3 = LocalDateTime.now().minusMinutes(1);
			LocalDateTime date4 = LocalDateTime.now().minusMinutes(4000);

			orderRepository.save(new Order(1L, 20, 8259.01, customer1, orderedProducts1, afleverAdres1, factuurAdres1, Status.VERZONDEN, date3));
			orderRepository.save(new Order(2L, 20, 8259.01, customer1, orderedProducts1, afleverAdres1, factuurAdres1, Status.INGEPAKT, date2));
			orderRepository.save(new Order(3L, 20, 1672.86, customer2, orderedProducts2, afleverAdres1, factuurAdres1, Status.IN_BEHANDELING, date1));
			orderRepository.save(new Order(4L, 20, 21501.74, customer3, orderedProducts2, afleverAdres1, factuurAdres1, Status.IN_BEHANDELING, date4));

			System.out.println("All orders added");
			orderRepository.findAll().forEach(System.out::println);
		};
	}
}