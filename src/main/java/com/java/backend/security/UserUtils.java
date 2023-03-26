package com.java.backend.security;

import com.java.backend.constant.ResponseStatus;
import com.java.backend.dto.request.CreateUserRequest;
import com.java.backend.entity.User;
import com.java.backend.exception.BookCarException;
import com.java.backend.repository.UserRepository;
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

    private final UserRepository userRepository;

    public User getCurrentUserLogin() {
        SecurityContext context = SecurityContextHolder.getContext();

        if (context.getAuthentication().getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User) context
                .getAuthentication()
                .getPrincipal();
            return userRepository
                .findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new BookCarException(ResponseStatus.USER_NAME_IS_INVALID));
        }

        return null;
    }

    public String generateAvatarPathFor(CreateUserRequest user) {
        return String.join("/", rootDir, user.getUsername());
    }
}
