package com.mybank.banking_app.controllers;

import com.mybank.banking_app.dtos.request.LoginRequestDto;
import com.mybank.banking_app.dtos.response.LoginResponseDto;
import com.mybank.banking_app.entities.User;
import com.mybank.banking_app.repositories.UserRepository;
import com.mybank.banking_app.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody @Valid LoginRequestDto request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(), request.getPassword())
                );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(); // already validated

        String token = jwtUtil.generateToken(user);

        List<String> roles = user.getRoles()
                .stream().map(r -> r.getRoleName().name()).toList();

        return ResponseEntity.ok(
                new LoginResponseDto(token, user.getUsername(), roles)
        );
    }
}

