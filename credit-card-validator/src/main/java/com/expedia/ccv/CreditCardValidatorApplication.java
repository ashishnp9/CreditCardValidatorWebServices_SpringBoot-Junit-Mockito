package com.expedia.ccv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class CreditCardValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardValidatorApplication.class, args);
	}
}
