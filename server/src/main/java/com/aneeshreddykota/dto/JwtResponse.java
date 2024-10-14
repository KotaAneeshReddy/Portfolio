package com.aneeshreddykota.dto;

public class JwtResponse {

    private String token;
    private String type = "Bearer";

    // Constructor to initialize the token
    public JwtResponse(String token) {
        this.token = token;
    }

    // Getter for the token
    public String getToken() {
        return token;
    }

    // Getter for the token type (optional)
    public String getType() {
        return type;
    }

    // Optionally, you can add setters if needed (though not required for a response object)
    public void setToken(String token) {
        this.token = token;
    }

    public void setType(String type) {
        this.type = type;
    }
}

