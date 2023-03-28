package com.java.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NotBlank
@AllArgsConstructor
public class AuthorityResponse {
    private String groupName;
    private List<Authority> authorities;

    @Getter
    @Setter
    @NotBlank
    @AllArgsConstructor
    public static class Authority{
        private String code;
        private String name;
    }
}
