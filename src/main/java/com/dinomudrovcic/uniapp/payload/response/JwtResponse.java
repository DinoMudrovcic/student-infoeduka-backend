package com.dinomudrovcic.uniapp.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
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

}
