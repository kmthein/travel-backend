package com.travelbackend.dto;

import org.springframework.security.core.userdetails.UserDetails;

public class ResponseTokenDTO {
    private String Token;
    private UserDTO userDetails;

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

    public UserDTO getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDTO userDetails) {
        this.userDetails = userDetails;
    }
}
