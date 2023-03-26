package com.java.backend.service;

import com.java.backend.dto.response.BasicUserInfoResponse;
import com.java.backend.dto.response.PageResponse;
import com.java.backend.entity.User;
import com.java.backend.exception.FileException;
import com.java.backend.exception.UserException;
import java.io.IOException;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User fetchCurrentUserInfo() throws UserException;

    User createUser(User user);

    void updateUser(User user);

    User findByUsername(String username);

    PageResponse<User> findAll(Pageable pageable);

    void deleteByUsername(String username);

    BasicUserInfoResponse getBasicInfo() throws FileException;
}
