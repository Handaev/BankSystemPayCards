package com.example.BankCardManagementSystems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.BankCardManagementSystems.entity")
@EnableJpaRepositories(basePackages = "com.example.BankCardManagementSystems.repository")
public class BankCardManagementSystemsApplication {

	public static void main(String[] args) {

		SpringApplication.run(BankCardManagementSystemsApplication.class, args);
	}

}
