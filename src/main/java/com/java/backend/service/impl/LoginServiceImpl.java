package com.java.backend.service.impl;

import com.java.backend.constant.ResponseStatus;
import com.java.backend.dto.request.LoginRequest;
import com.java.backend.dto.response.LoginResponse;
import com.java.backend.entity.User;
import com.java.backend.exception.BookCarException;
import com.java.backend.repository.UserRepository;
import com.java.backend.security.jwtutils.TokenManager;
import com.java.backend.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final TokenManager tokenManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse authenticate(LoginRequest request) throws BookCarException {

        final User user = userRepository
            .findByUsername(request.getUsername())
            .orElseThrow(() -> new BookCarException(ResponseStatus.USERNAME_NOT_FOUND));

        if (!isPasswordCorrect(user, request)) {
            throw new BookCarException(ResponseStatus.INVALID_CREDENTIALS);
        }

        String token = tokenManager.generateJwtToken(user);
        return new LoginResponse(token, true);
    }

    boolean isPasswordCorrect(User user, LoginRequest request) {
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
