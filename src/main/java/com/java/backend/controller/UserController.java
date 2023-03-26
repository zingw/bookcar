package com.java.backend.controller;

import com.java.backend.dto.response.BasicUserInfoResponse;
import com.java.backend.exception.FileException;
import com.java.backend.service.UserService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/basic-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasicUserInfoResponse> getBasicInfo() throws FileException {
        BasicUserInfoResponse response = userService.getBasicInfo();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
