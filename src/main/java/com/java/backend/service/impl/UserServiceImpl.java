package com.java.backend.service.impl;

import com.java.backend.constant.ResponseStatus;
import com.java.backend.dto.request.ChangePasswordRequest;
import com.java.backend.dto.request.CreateUserRequest;
import com.java.backend.dto.request.UpdateUserRequest;
import com.java.backend.dto.response.BasicUserInfoResponse;
import com.java.backend.dto.response.PageResponse;
import com.java.backend.entity.User;
import com.java.backend.exception.BookCarException;
import com.java.backend.repository.UserRepository;
import com.java.backend.service.UserService;
import com.java.backend.utils.UserUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserUtils userUtils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User fetchCurrentUserInfo() throws BookCarException {
        return userUtils.getCurrentUserLogin();
    }

    @Override
    public User createUser(CreateUserRequest newUser) {
        if (userExisted(newUser.getUsername(), newUser.getEmail(), newUser.getPhoneNumber())) {
            throw new BookCarException(ResponseStatus.USER_EXISTED);
        }

        User user = User
            .builder()
            .username(newUser.getUsername())
            .fullName(newUser.getFullName())
            .password(passwordEncoder.encode(newUser.getPassword()))
            .role(newUser.getRole())
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
    public void updateUser(UpdateUserRequest user) {
        String id = user.getId();
        userRepository
            .findById(id)
            .ifPresent(existUser -> {
                existUser.setUsername(user.getUsername());
                existUser.setFullName(user.getFullName());
                existUser.setEmail(user.getEmail());

                userRepository.save(existUser);
            });
        throw new BookCarException(ResponseStatus.USER_ID_IS_INVALID);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new BookCarException(ResponseStatus.USER_NAME_IS_INVALID));
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
        throw new BookCarException(ResponseStatus.USER_NAME_IS_INVALID);
    }

    @Override
    public BasicUserInfoResponse getBasicInfo() throws BookCarException {
        User user = userUtils.getCurrentUserLogin();

        File ava = new File(user.getAvatarPath());
        byte[] ava_bytes;
        try {
            ava_bytes = Files.readAllBytes(ava.toPath());
        } catch (IOException e) {
            throw new BookCarException(ResponseStatus.CAN_NOT_PARSE_FILE);
        }
        return BasicUserInfoResponse.builder().role(user.getRole()).username(user.getUsername()).avatar(ava_bytes).build();
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        User user = userUtils.getCurrentUserLogin();
        String hashedPassword = user.getPassword();
        if (isMatch(request.getOldPassword(), hashedPassword)) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }
        throw new BookCarException(ResponseStatus.PASSWORD_INCORRECT);
    }

    public boolean userExisted(String username, String email, String phoneNumber) {
        return userRepository.existsByUsernameOrEmailOrPhoneNumberAndActivatedTrue(username, email, phoneNumber);
    }

    public boolean isMatch(String oldPassword, String hasedStorePassword) {
        return BCrypt.checkpw(oldPassword, hasedStorePassword);
    }
}
