package com.java.backend.service;

import com.java.backend.entity.User;
import com.java.backend.exception.UserException;

public interface UserService {
    User fetchCurrentUserInfo() throws UserException;
}
