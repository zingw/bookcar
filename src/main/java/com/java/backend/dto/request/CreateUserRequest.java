package com.java.backend.dto.request;

import com.java.backend.entity.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateUserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String fullName;

    private Role role;

    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String password;
}
