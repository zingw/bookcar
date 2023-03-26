package com.java.backend.security;

import com.java.backend.constant.Code;
import com.java.backend.entity.User;
import com.java.backend.exception.UserException;
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
            return userRepository.findByUsername(currentUser.getUsername()).orElseThrow(() -> new UserException(Code.USER_NOT_FOUND));
        }

        return null;
    }

    public String generateAvatarPathFor(User user) {
        return String.join("/", rootDir, user.getUsername());
    }
}
