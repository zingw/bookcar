package com.java.backend.service;

import com.java.backend.dto.request.RegisterRequest;
import com.java.backend.dto.response.RegisterResponse;
import com.java.backend.exception.BookCarException;

public interface RegisterService {
    RegisterResponse register(RegisterRequest request) throws BookCarException;
}
