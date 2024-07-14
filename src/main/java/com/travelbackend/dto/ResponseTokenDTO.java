package com.travelbackend.dto;

public class ResponseTokenDTO {
    private String Token;

    public ResponseTokenDTO(){}

    public ResponseTokenDTO(String token) {
        Token = token;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
