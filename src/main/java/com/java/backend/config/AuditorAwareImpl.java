package com.java.backend.config;

import com.java.backend.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        SecurityContext context = SecurityContextHolder.getContext();

        if (context.getAuthentication().getPrincipal() instanceof User) {
            return Optional.ofNullable(((User) context.getAuthentication().getPrincipal()).getId());
        }
        return Optional.empty();
    }
}
