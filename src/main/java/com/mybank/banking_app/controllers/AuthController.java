package com.mybank.banking_app.controllers;

import com.mybank.banking_app.dtos.request.LoginRequestDto;
import com.mybank.banking_app.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/api/authenticate")
    public String generateToken(@RequestBody LoginRequestDto loginRequestDto) {
        // validating the credentials and then token will be created
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
            );
            // create jwt token
             return jwtUtil.generateToken(loginRequestDto.getUsername());

        } catch (Exception ex){
            throw ex;
        }
    }
}
