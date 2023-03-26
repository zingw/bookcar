package com.java.backend.controller;

import com.java.backend.dto.response.PageResponse;
import com.java.backend.entity.User;
import com.java.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/user")
public class UserManagementController {

    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User response = userService.createUser(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }

    @GetMapping("/find-by-username/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        User response = userService.findByUsername(username);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/query")
    public ResponseEntity<PageResponse<User>> query(Pageable pageable) {
        PageResponse<User> response = userService.findAll(pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<?> deleteByUsername(@PathVariable String username) {
        userService.deleteByUsername(username);
        return new ResponseEntity<>(username, HttpStatus.OK);
    }
}
