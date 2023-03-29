package com.java.backend.service;

import com.java.backend.dto.request.ChangePasswordRequest;
import com.java.backend.dto.request.CreateUserRequest;
import com.java.backend.dto.request.UpdateUserRequest;
import com.java.backend.dto.response.BasicUserInfoResponse;
import com.java.backend.dto.response.PageResponse;
import com.java.backend.dto.response.UserResponse;
import com.java.backend.entity.User;
import com.java.backend.exception.BookCarException;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User fetchCurrentUserInfo() throws BookCarException;

    UserResponse createUser(CreateUserRequest user);

    void updateUser(UpdateUserRequest user);

    UserResponse findById(String username);

    PageResponse<UserResponse> findAll(Pageable pageable);

    void deleteByUsername(String username);

    BasicUserInfoResponse getBasicInfo() throws BookCarException;

    void changePassword(ChangePasswordRequest request);
}
