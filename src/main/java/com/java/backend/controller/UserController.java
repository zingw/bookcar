package com.java.backend.controller;

import com.java.backend.dto.request.ChangePasswordRequest;
import com.java.backend.dto.response.BasicUserInfoResponse;
import com.java.backend.exception.BookCarException;
import com.java.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/basic-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasicUserInfoResponse> getBasicInfo() throws BookCarException {
        BasicUserInfoResponse response = userService.getBasicInfo();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        userService.changePassword(request);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
