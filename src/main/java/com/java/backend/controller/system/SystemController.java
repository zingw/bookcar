package com.java.backend.controller.system;

import com.java.backend.dto.response.AuthorityResponse;
import com.java.backend.entity.User;
import com.java.backend.service.SystemService;
import com.java.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SystemController {

    private final SystemService systemService;
    private final UserService userService;

    @GetMapping("/account")
    public ResponseEntity<User> fetchCurrentUserInfo() {
        User response = userService.fetchCurrentUserInfo();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/authorities")
    public ResponseEntity<List<AuthorityResponse>> getAllAuthorities() {
        List<AuthorityResponse> responseList = systemService.getAllAuthorities();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
