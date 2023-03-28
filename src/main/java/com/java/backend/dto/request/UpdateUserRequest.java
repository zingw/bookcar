package com.java.backend.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserRequest {

    @NotBlank
    private String id;

    @NotBlank
    private String username;

    @NotBlank
    private String fullName;

    @NotBlank
    private String email;
}
