package com.github.login_service.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.login_service.request.LoginRequest;
import com.github.login_service.DTO.ExternalUserDTO;
import com.github.login_service.request.AuthResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {

        String url = "http://localhost:8082/api-v1/register/" + request.getUsername();
        ExternalUserDTO externalUser =
                restTemplate.getForObject(url, ExternalUserDTO.class);

        if (externalUser == null) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(request.getPassword(), externalUser.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(
                externalUser.getUsername(),
                externalUser.getRole()
        );

        return AuthResponse.builder()
                .token(token)
                .build();
    }
}

