package com.bajajfinserv.webhook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for webhook generation request
 */
public class WebhookGenerationRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("regNo")
    private String regNo;

    @JsonProperty("email")
    private String email;

    public WebhookGenerationRequest() {}

    public WebhookGenerationRequest(String name, String regNo, String email) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "WebhookGenerationRequest{" +
                "name='" + name + '\'' +
                ", regNo='" + regNo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}