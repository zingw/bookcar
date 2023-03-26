package com.java.backend.service.impl;

import com.java.backend.dto.request.LoginRequest;
import com.java.backend.dto.response.LoginResponse;
import com.java.backend.exception.UserException;
import com.java.backend.security.jwtutils.TokenManager;
import com.java.backend.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final TokenManager tokenManager;
    private static final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";

    @Override
    public LoginResponse authenticate(LoginRequest request) throws UserException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception e) {
            throw new UserException(INVALID_CREDENTIALS);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = tokenManager.generateJwtToken(userDetails);
        return new LoginResponse(token, true);
    }
}
