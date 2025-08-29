package com.bajajfinserv.webhook;

import com.bajajfinserv.webhook.dto.WebhookGenerationRequest;
import com.bajajfinserv.webhook.dto.WebhookGenerationResponse;
import com.bajajfinserv.webhook.dto.SolutionSubmissionRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service class to handle webhook generation and solution submission
 */
@Service
public class WebhookService {

    private final RestTemplate restTemplate;
    private final SqlQueryService sqlQueryService;

    private static final String WEBHOOK_GENERATION_URL = 
        "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

    private static final String SOLUTION_SUBMISSION_URL = 
        "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

    public WebhookService(RestTemplate restTemplate, SqlQueryService sqlQueryService) {
        this.restTemplate = restTemplate;
        this.sqlQueryService = sqlQueryService;
    }

    /**
     * Execute the complete webhook workflow:
     * 1. Generate webhook
     * 2. Get SQL solution
     * 3. Submit solution
     */
    public void executeWebhookWorkflow() {
        try {
            // Step 1: Generate webhook
            System.out.println("Step 1: Generating webhook...");
            WebhookGenerationResponse webhookResponse = generateWebhook();

            // Step 2: Get SQL solution for Question 2
            System.out.println("Step 2: Generating SQL solution for Question 2...");
            String sqlQuery = sqlQueryService.getQuestion2Solution();

            // Step 3: Submit solution
            System.out.println("Step 3: Submitting solution...");
            submitSolution(webhookResponse.getWebhook(), webhookResponse.getAccessToken(), sqlQuery);

        } catch (Exception e) {
            System.err.println("Error in webhook workflow: " + e.getMessage());
            throw new RuntimeException("Webhook workflow failed", e);
        }
    }

    /**
     * Generate webhook by sending POST request with registration details
     */
    private WebhookGenerationResponse generateWebhook() {
        try {
            // Create request payload
            WebhookGenerationRequest request = new WebhookGenerationRequest(
                "Student Name",  // Replace with actual name
                "22BCE0538",     // Registration number (even - Question 2)
                "student@example.com"  // Replace with actual email
            );

            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<WebhookGenerationRequest> entity = new HttpEntity<>(request, headers);

            System.out.println("Sending POST request to: " + WEBHOOK_GENERATION_URL);
            System.out.println("Request payload: " + request);

            // Send POST request
            ResponseEntity<WebhookGenerationResponse> response = restTemplate.exchange(
                WEBHOOK_GENERATION_URL,
                HttpMethod.POST,
                entity,
                WebhookGenerationResponse.class
            );

            WebhookGenerationResponse webhookResponse = response.getBody();

            if (webhookResponse != null) {
                System.out.println("Webhook generated successfully!");
                System.out.println("Webhook URL: " + webhookResponse.getWebhook());
                System.out.println("Access Token: " + webhookResponse.getAccessToken());
                return webhookResponse;
            } else {
                throw new RuntimeException("No response received from webhook generation");
            }

        } catch (Exception e) {
            System.err.println("Error generating webhook: " + e.getMessage());
            throw new RuntimeException("Failed to generate webhook", e);
        }
    }

    /**
     * Submit the SQL solution to the webhook URL using JWT authentication
     */
    private void submitSolution(String webhookUrl, String accessToken, String sqlQuery) {
        try {
            // Create solution request
            SolutionSubmissionRequest request = new SolutionSubmissionRequest(sqlQuery);

            // Set headers with JWT token
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", accessToken);

            HttpEntity<SolutionSubmissionRequest> entity = new HttpEntity<>(request, headers);

            System.out.println("Submitting solution to: " + SOLUTION_SUBMISSION_URL);
            System.out.println("SQL Query: " + sqlQuery);

            // Send POST request
            ResponseEntity<String> response = restTemplate.exchange(
                SOLUTION_SUBMISSION_URL,
                HttpMethod.POST,
                entity,
                String.class
            );

            System.out.println("Solution submitted successfully!");
            System.out.println("Response: " + response.getBody());

        } catch (Exception e) {
            System.err.println("Error submitting solution: " + e.getMessage());
            throw new RuntimeException("Failed to submit solution", e);
        }
    }
}