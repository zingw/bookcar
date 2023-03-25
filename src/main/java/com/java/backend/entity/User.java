package com.java.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "user")
public class User extends Auditor {

    @Id
    private String id;

    private String username;
    private String email;
    private String phoneNumber;
    private List<Authority> authorityList;

    @JsonIgnore
    private String password;

    private boolean activated;

    public List<GrantedAuthority> getGrantedAuthorityList() {
        return this.authorityList.stream().map(auth -> (GrantedAuthority) auth::name).collect(Collectors.toList());
    }
}
