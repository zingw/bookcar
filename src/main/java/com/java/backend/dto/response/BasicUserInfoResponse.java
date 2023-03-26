package com.java.backend.dto.response;

import com.java.backend.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasicUserInfoResponse {

    private String username;
    private Role role;
    private byte[] avatar;
}
