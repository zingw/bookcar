package com.java.backend.service.impl;

import com.java.backend.constant.Code;
import com.java.backend.dto.response.BasicUserInfoResponse;
import com.java.backend.dto.response.PageResponse;
import com.java.backend.entity.User;
import com.java.backend.exception.FileException;
import com.java.backend.exception.UserException;
import com.java.backend.repository.UserRepository;
import com.java.backend.security.UserUtils;
import com.java.backend.service.UserService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserUtils userUtils;

    @Override
    public User fetchCurrentUserInfo() throws UserException {
        return userUtils.getCurrentUserLogin();
    }

    @Override
    public User createUser(User newUser) {
        if (userExisted(newUser.getUsername(), newUser.getEmail(), newUser.getPhoneNumber())) {
            throw new UserException(Code.USER_EXISTED);
        }

        User user = User
            .builder()
            .username(newUser.getUsername())
            .fullName(newUser.getFullName())
            .email(newUser.getEmail())
            .phoneNumber(newUser.getPhoneNumber())
            .avatarPath(userUtils.generateAvatarPathFor(newUser))
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
            throw new UserException(Code.USER_ID_IS_EMPTY);
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
        throw new UserException(Code.USER_ID_IS_INVALID);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserException(Code.USERNAME_NOT_FOUND));
    }

    @Override
    public PageResponse<User> findAll(Pageable pageable) {
        Page<User> pageUser = userRepository.findByDeletedFalse(pageable);
        return new PageResponse<>(pageUser.getContent(), pageUser.getTotalElements());
    }

    @Override
    public void deleteByUsername(String username) {
        userRepository
            .findByUsername(username)
            .ifPresent(user -> {
                user.setDeleted(true);
            });
        throw new UserException(Code.USER_NAME_IS_INVALID);
    }

    @Override
    public BasicUserInfoResponse getBasicInfo() throws FileException {
        User user = userUtils.getCurrentUserLogin();

        File ava = new File(user.getAvatarPath());
        byte[] ava_bytes;
        try {
            ava_bytes = Files.readAllBytes(ava.toPath());
        } catch (IOException e) {
            throw new FileException(Code.CAN_NOT_PARSE_FILE);
        }
        return BasicUserInfoResponse.builder().role(user.getRole()).username(user.getUsername()).avatar(ava_bytes).build();
    }

    public boolean userExisted(String username, String email, String phoneNumber) {
        return userRepository.existsByUsernameOrEmailOrPhoneNumberAndActivatedTrue(username, email, phoneNumber);
    }
}
