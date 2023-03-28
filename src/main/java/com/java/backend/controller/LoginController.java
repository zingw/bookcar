package com.java.backend.controller;

import com.java.backend.dto.request.LoginRequest;
import com.java.backend.dto.response.LoginResponse;
import com.java.backend.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = loginService.authenticate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
