package com.synclab.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.synclab.ecommerce.utility.LoggerUtil;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
        runningMessage();
    }

    public static void runningMessage() {
        System.out.println("Ecommerce started (✓) - Listening on port 8080 ...");
        System.out.println("");
    }

}
