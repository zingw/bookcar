package com.java.backend.service.impl;

import com.java.backend.entity.User;
import com.java.backend.exception.UserException;
import com.java.backend.repository.UserRepository;
import com.java.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private static final String USER_LOGGED_OUT = "USER_LOGGED_OUT";

    @Override
    public User fetchCurrentUserInfo() throws UserException {
        SecurityContext context = SecurityContextHolder.getContext();

        if (context.getAuthentication().getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User) context
                .getAuthentication()
                .getPrincipal();
            return userRepository.findByUsername(currentUser.getUsername()).orElseThrow(() -> new UserException(USER_LOGGED_OUT));
        }
        return null;
    }
}
