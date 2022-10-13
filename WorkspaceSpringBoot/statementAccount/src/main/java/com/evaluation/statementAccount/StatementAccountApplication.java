package com.evaluation.statementAccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StatementAccountApplication {

	public static void main(String[] args) {
		System.setProperty("server.port", "8091");
		SpringApplication.run(StatementAccountApplication.class, args);
	}

}
