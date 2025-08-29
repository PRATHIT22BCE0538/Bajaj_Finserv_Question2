package com.bajajfinserv.webhook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for solution submission request
 */
public class SolutionSubmissionRequest {

    @JsonProperty("finalQuery")
    private String finalQuery;

    public SolutionSubmissionRequest() {}

    public SolutionSubmissionRequest(String finalQuery) {
        this.finalQuery = finalQuery;
    }

    // Getters and Setters
    public String getFinalQuery() {
        return finalQuery;
    }

    public void setFinalQuery(String finalQuery) {
        this.finalQuery = finalQuery;
    }

    @Override
    public String toString() {
        return "SolutionSubmissionRequest{" +
                "finalQuery='" + finalQuery + '\'' +
                '}';
    }
}