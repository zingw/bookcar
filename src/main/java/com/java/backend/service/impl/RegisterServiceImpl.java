package com.java.backend.service.impl;

import com.java.backend.constant.UserConstant;
import com.java.backend.dto.request.RegisterRequest;
import com.java.backend.dto.response.RegisterResponse;
import com.java.backend.entity.PendingRegister;
import com.java.backend.entity.User;
import com.java.backend.exception.UserException;
import com.java.backend.repository.PendingRegisterRepository;
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
    private final PendingRegisterRepository pendingRegisterRepository;

    @Override
    public RegisterResponse register(RegisterRequest request) throws UserException {
        if (
            userRepository.existsByUsernameOrEmailOrPhoneNumberAndActivatedTrue(
                request.getUsername(),
                request.getEmail(),
                request.getPhoneNumber()
            )
        ) {
            throw new UserException(UserConstant.USER_EXISTED);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAuthorities(Collections.emptyList());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setActivated(false);

        user = userRepository.save(user);
        pendingRegisterRepository.save(new PendingRegister(user.getId()));
        return new RegisterResponse(user.getId());
    }
}
