package com.github.login_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.login_service.DTO.ExternalUserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestService {

     private final RestTemplate restTemplate;

    public ExternalUserDTO getUser(String username) {
        String url = "http://localhost:8082/api-v1/register/" + username;
        return restTemplate.getForObject(url, ExternalUserDTO.class);
    }

}
