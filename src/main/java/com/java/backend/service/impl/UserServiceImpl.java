package com.java.backend.service.impl;

import com.java.backend.entity.User;
import com.java.backend.exception.ExceptionUtils;
import com.java.backend.repository.UserRepository;
import com.java.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User fetchCurrentUserInfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User) context
            .getAuthentication()
            .getPrincipal();
        return userRepository.findByUsername(currentUser.getUsername()).orElseThrow(ExceptionUtils.UNKNOWN_ERROR::build);
    }
}
