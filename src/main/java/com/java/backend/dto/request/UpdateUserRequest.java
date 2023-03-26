package com.java.backend.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

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
