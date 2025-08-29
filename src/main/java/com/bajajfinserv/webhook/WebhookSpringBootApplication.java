package com.bajajfinserv.webhook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Main Spring Boot Application for Bajaj Finserv Health Qualifier
 * Registration Number: 22BCE0538 (Even - Question 2)
 * 
 * This application:
 * 1. Generates webhook on startup
 * 2. Solves SQL problem (Question 2)  
 * 3. Submits solution using JWT authentication
 */
@SpringBootApplication
public class WebhookSpringBootApplication implements CommandLineRunner {

    private final WebhookService webhookService;

    public WebhookSpringBootApplication(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebhookSpringBootApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Bajaj Finserv Health Qualifier - Starting Webhook Process ===");
        System.out.println("Registration Number: 22BCE0538 (Even - Question 2)");
        System.out.println();

        try {
            // Execute the complete webhook workflow
            webhookService.executeWebhookWorkflow();

            System.out.println("=== Webhook Process Completed Successfully ===");
        } catch (Exception e) {
            System.err.println("Error in webhook process: " + e.getMessage());
            e.printStackTrace();
        }
    }
}