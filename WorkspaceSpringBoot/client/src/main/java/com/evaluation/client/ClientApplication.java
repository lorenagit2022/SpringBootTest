package com.evaluation.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		System.setProperty("server.port", "8088");
		SpringApplication.run(ClientApplication.class, args);
	}

	
}
