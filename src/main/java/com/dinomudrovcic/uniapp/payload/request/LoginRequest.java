package com.dinomudrovcic.uniapp.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
