package com.java.backend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    @JsonProperty("login")
    private String username;

    private String password;
    private String email;
}
