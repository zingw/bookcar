package com.java.backend.service.impl;

import com.java.backend.constant.UserConstant;
import com.java.backend.dto.response.PageResponse;
import com.java.backend.entity.User;
import com.java.backend.exception.UserException;
import com.java.backend.repository.UserRepository;
import com.java.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
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

    @Override
    public User createUser(User newUser) {
        if (userExisted(newUser.getUsername(), newUser.getEmail(), newUser.getPhoneNumber())) {
            throw new UserException(UserConstant.USER_EXISTED);
        }

        User user = User
            .builder()
            .username(newUser.getUsername())
            .fullName(newUser.getFullName())
            .email(newUser.getEmail())
            .phoneNumber(newUser.getPhoneNumber())
            .activated(true)
            .build();

        return userRepository.save(user);
    }

    /**
     * Only update basic information
     */
    @Override
    public void updateUser(User user) {
        if (StringUtils.isBlank(user.getId())) {
            throw new UserException(UserConstant.USER_ID_IS_EMPTY);
        }

        String id = user.getId();

        userRepository
            .findById(id)
            .ifPresent(existUser -> {
                existUser.setUsername(user.getUsername());
                existUser.setFullName(user.getFullName());
                existUser.setEmail(user.getEmail());

                userRepository.save(existUser);
            });
        throw new UserException(UserConstant.USER_ID_IS_INVALID);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserException(UserConstant.USERNAME_NOT_FOUND));
    }

    @Override
    public PageResponse<User> findAll(Pageable pageable) {
        Page<User> pageUser = userRepository.findByDeletedFalse(pageable);
        return PageResponse.<User>builder().content(pageUser.getContent()).totalElement(pageUser.getTotalElements()).build();
    }

    @Override
    public void deleteByUsername(String username) {
        userRepository
            .findByUsername(username)
            .ifPresent(user -> {
                user.setDeleted(true);
            });
        throw new UserException(UserConstant.USER_NAME_IS_INVALID);
    }

    public boolean userExisted(String username, String email, String phoneNumber) {
        return userRepository.existsByUsernameOrEmailOrPhoneNumberAndActivatedTrue(username, email, phoneNumber);
    }
}
