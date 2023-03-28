package com.java.backend.utils;

import com.java.backend.dto.request.CreateUserRequest;
import com.java.backend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUtils {

    @Value("${file.root}")
    private static String rootDir;

    public User getCurrentUserLogin() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context.getAuthentication().getPrincipal() instanceof User) {
            return (User) context.getAuthentication().getPrincipal();
        }
        return null;
    }

    public String generateAvatarPathFor(CreateUserRequest user) {
        return String.join("/", rootDir, user.getUsername());
    }
}
