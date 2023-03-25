package com.java.backend.controller;

import com.java.backend.dto.request.RegisterRequest;
import com.java.backend.dto.response.RegisterResponse;
import com.java.backend.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = registerService.register(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
