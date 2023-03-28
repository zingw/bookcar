package com.java.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.backend.enums.Authority;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Document(collection = "user")
public class User extends Auditor {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String fullName;

    @Indexed(unique = true)
    private String email;

    private String avatarPath;
    private Role role;

    @Indexed(unique = true)
    private String phoneNumber;

    private List<Authority> authorities;

    @JsonIgnore
    private String password;

    private Boolean deleted;
    private Boolean activated;

    @JsonIgnore
    public List<GrantedAuthority> getGrantedAuthorityList() {
        return this.authorities.stream().map(auth -> (GrantedAuthority) auth::name).collect(Collectors.toList());
    }
}
