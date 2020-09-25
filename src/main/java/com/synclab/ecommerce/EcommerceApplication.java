package com.synclab.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
		clearScreen();
	}

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("Spring server started (✓) - Waiting for commands...");
	}

}
