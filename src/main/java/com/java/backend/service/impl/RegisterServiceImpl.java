package com.java.backend.service.impl;

import com.java.backend.constant.Code;
import com.java.backend.dto.request.RegisterRequest;
import com.java.backend.dto.response.RegisterResponse;
import com.java.backend.entity.User;
import com.java.backend.exception.UserException;
import com.java.backend.repository.UserRepository;
import com.java.backend.service.RegisterService;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest request) throws UserException {
        if (
            userRepository.existsByUsernameOrEmailOrPhoneNumberAndActivatedTrue(
                request.getUsername(),
                request.getEmail(),
                request.getPhoneNumber()
            )
        ) {
            throw new UserException(Code.USER_EXISTED);
        }

        User user = User
            .builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .phoneNumber(request.getPhoneNumber())
            .activated(false)
            .deleted(false)
            .authorities(Collections.emptyList())
            .build();

        user = userRepository.save(user);
        return new RegisterResponse(user.getId());
    }
}
