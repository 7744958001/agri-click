package com.agritech.agritech_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.agritech.agritech_app.entity.User;
import com.agritech.agritech_app.repositery.UserRepository;

@SpringBootApplication
public class AgritechAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgritechAppApplication.class, args);
		System.out.println();
	}
}
