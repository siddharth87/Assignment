package com.example.assignment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AuthResponse {
    private boolean isSuccess;
    private String token;
    private String username;
    private String role;
    private String responseDescription;

    // No-args constructor
    public AuthResponse() {
    }

    // Parameterized constructor
    public AuthResponse(String token, String username, String responseDescription, boolean isSuccess) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.isSuccess = isSuccess;
        this.responseDescription = responseDescription;
    }
}