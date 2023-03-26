package com.java.backend.service;

import com.java.backend.dto.request.LoginRequest;
import com.java.backend.dto.response.LoginResponse;
import com.java.backend.exception.BookCarException;

public interface LoginService {
    LoginResponse authenticate(LoginRequest request) throws BookCarException;
}
