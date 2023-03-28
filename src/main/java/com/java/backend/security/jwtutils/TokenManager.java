package com.java.backend.security.jwtutils;

import com.java.backend.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class TokenManager implements Serializable {

    private static final long serialVersionUID = 7008375124389347049L;
    private static final long TOKEN_VALIDITY = 10 * 60 * 60;
    private static final String AUTHORITY_LIST = "authorities";

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(AUTHORITY_LIST, userDetails.getAuthorities());
        return Jwts
            .builder()
            .setClaims(claims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
            .signWith(SignatureAlgorithm.HS256, jwtSecret)
            .compact();
    }

    public Boolean validateJwtToken(String token, User user) {
        String username = getUsernameFromToken(token);
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (username.equals(user.getUsername()) && !isTokenExpired);
    }

    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
