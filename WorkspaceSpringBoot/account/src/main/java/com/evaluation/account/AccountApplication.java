package com.evaluation.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountApplication {

	public static void main(String[] args) {
		System.setProperty("server.port", "8089");
		SpringApplication.run(AccountApplication.class, args);
	}

}
