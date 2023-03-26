package com.java.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

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

    @Indexed(unique = true)
    private String phoneNumber;

    private List<Authority> authorities;

    @JsonIgnore
    private String password;

    private boolean deleted;
    private boolean activated;

    @JsonIgnore
    public List<GrantedAuthority> getGrantedAuthorityList() {
        return this.authorities.stream().map(auth -> (GrantedAuthority) auth::name).collect(Collectors.toList());
    }
}
