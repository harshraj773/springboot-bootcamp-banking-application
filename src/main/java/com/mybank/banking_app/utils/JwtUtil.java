package com.mybank.banking_app.utils;

import com.mybank.banking_app.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET =
            "banking-secret-key-very-secure-256-bit-banking-secret-key";

    private static final long EXPIRY =
            24 * 60 * 60 * 1000;

    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(User user) {

        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getId())
                .claim("personId", user.getPerson().getPersonId())
                .claim("roles", user.getRoles()
                        .stream()
                        .map(r -> r.getRoleName().name())
                        .toList())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRY))
                .signWith(key) // algorithm auto-detected (HS256)
                .compact();
    }
}


