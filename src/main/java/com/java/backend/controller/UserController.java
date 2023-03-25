package com.java.backend.controller;

import com.java.backend.entity.User;
import com.java.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/account")
    public ResponseEntity<User> fetchCurrentUserInfo() {
        User response = userService.fetchCurrentUserInfo();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
