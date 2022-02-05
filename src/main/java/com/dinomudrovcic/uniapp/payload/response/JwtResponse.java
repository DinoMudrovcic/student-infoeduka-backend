package com.dinomudrovcic.uniapp.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
