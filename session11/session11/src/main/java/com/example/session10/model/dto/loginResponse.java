package com.example.session10.model.dto;

public class loginResponse {
    private String accessToken;
    private String type;
    private String username;

    public loginResponse() {
    }

    public loginResponse(String accessToken, String type, String username) {
        this.accessToken = accessToken;
        this.type = type;
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
