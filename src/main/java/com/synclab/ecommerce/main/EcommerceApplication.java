package com.synclab.ecommerce.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
		runningMessage();
	}

	public static void runningMessage() {
		System.out.println("Spring server started (✓) - Waiting for commands...");
	}

}
