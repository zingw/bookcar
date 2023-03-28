package com.java.backend.security.jwtutils;

import com.java.backend.constant.ResponseStatus;
import com.java.backend.entity.User;
import com.java.backend.exception.BookCarException;
import com.java.backend.repository.UserRepository;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final TokenManager tokenManager;
    private static final int BEGIN_INDEX = 7;
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String username = null;
        String token = null;

        if (tokenHeader != null && tokenHeader.startsWith(TOKEN_PREFIX)) {
            token = tokenHeader.substring(BEGIN_INDEX);
            username = tokenManager.getUsernameFromToken(token);
        }

        if (null != username && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new BookCarException(ResponseStatus.INVALID_CREDENTIALS));

            if (tokenManager.validateJwtToken(token, user)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getGrantedAuthorityList()
                );

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
