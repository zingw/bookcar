package com.java.backend.security.jwtutils;

import com.java.backend.exception.UserException;
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

    private static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    private static final String USER_NOT_ACTIVATED = "USER_NOT_ACTIVATED";
    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.java.backend.entity.User user = userRepository.findByUsername(username).orElseThrow(() -> new UserException(USER_NOT_FOUND));

        if (!user.isActivated()) {
            throw new UserException(USER_NOT_ACTIVATED);
        }

        return new User(username, user.getPassword(), user.getGrantedAuthorityList());
    }
}
