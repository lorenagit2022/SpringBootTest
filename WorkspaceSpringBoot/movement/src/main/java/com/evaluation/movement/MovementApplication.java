package com.evaluation.movement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovementApplication {

	public static void main(String[] args) {
		System.setProperty("server.port", "8090");
		SpringApplication.run(MovementApplication.class, args);
	}

}
