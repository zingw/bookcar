package com.java.backend.service;

import com.java.backend.dto.request.RegisterRequest;
import com.java.backend.dto.response.RegisterResponse;

public interface RegisterService {
    RegisterResponse register(RegisterRequest request);
}
