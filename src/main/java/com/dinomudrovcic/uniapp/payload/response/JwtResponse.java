package com.dinomudrovcic.uniapp.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
public class JwtResponse {

    @JsonIgnore
    private Long id;
    private String token;
    @JsonIgnore
    private String type = "Bearer";
    private String username;
    @JsonIgnore
    private List<String> roles;
    private int expiresIn;

    public JwtResponse(String token, String username, List<String> roles, int expirationDate) {
        this.token = token;
        this.username = username;
        this.roles = roles;
        this.expiresIn = expirationDate;
    }

    public JwtResponse(String token) {
        this.token = token;
    }
}
