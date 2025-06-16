package com.integration.bosta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.integration.bosta")
public class BostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BostaApplication.class, args);
	}

}
