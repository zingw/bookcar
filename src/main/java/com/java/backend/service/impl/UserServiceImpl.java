package com.java.backend.service.impl;

import com.java.backend.entity.User;
import com.java.backend.service.UserService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User fetchCurrentUserInfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        context.getAuthentication().getCredentials();
        return null;
    }
}
