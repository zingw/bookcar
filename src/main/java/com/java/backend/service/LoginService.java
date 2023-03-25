package com.java.backend.service;

import com.java.backend.dto.request.LoginRequest;
import com.java.backend.dto.response.LoginResponse;

public interface LoginService {
    LoginResponse authenticate(LoginRequest request);
}
