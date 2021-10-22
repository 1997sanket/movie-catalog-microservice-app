package com.kamble.userratingsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserRatingsServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserRatingsServiceApplication.class, args);
	}

}
