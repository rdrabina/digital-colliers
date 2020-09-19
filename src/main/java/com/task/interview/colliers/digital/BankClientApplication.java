package com.task.interview.colliers.digital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.task.interview.colliers.digital.repository")
public class BankClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankClientApplication.class, args);
	}

}
