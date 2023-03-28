package com.java.backend.security.jwtutils;

import com.java.backend.constant.ResponseStatus;
import com.java.backend.exception.BookCarException;
import com.java.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.java.backend.entity.User user = userRepository
            .findByUsername(username)
            .orElseThrow(() -> new BookCarException(ResponseStatus.USERNAME_NOT_FOUND));

        if (!user.getActivated()) {
            throw new BookCarException(ResponseStatus.USER_NOT_ACTIVATED);
        }

        return new User(username, user.getPassword(), user.getGrantedAuthorityList());
    }
}
