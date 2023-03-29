package com.java.backend.dto.response;

import com.java.backend.entity.User;
import lombok.*;

import java.time.ZonedDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private ZonedDateTime createdDate;
    private boolean activated;

    public UserResponse (User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.createdDate = user.getCreatedDate();
        this.activated = user.isActivated();
    }

}
