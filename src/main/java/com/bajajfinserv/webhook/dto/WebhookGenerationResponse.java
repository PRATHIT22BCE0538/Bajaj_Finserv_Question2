package com.bajajfinserv.webhook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for webhook generation response
 */
public class WebhookGenerationResponse {

    @JsonProperty("webhook")
    private String webhook;

    @JsonProperty("accessToken")
    private String accessToken;

    public WebhookGenerationResponse() {}

    public WebhookGenerationResponse(String webhook, String accessToken) {
        this.webhook = webhook;
        this.accessToken = accessToken;
    }

    // Getters and Setters
    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "WebhookGenerationResponse{" +
                "webhook='" + webhook + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}