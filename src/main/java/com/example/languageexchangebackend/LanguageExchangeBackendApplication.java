package com.example.languageexchangebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LanguageExchangeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanguageExchangeBackendApplication.class, args);
	}

}
