package com.github.register_service.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.github.register_service.model.Rol;
import com.github.register_service.model.User;
import com.github.register_service.repository.RolRepository;
import com.github.register_service.repository.UserRepository;
import com.github.register_service.request.RegisterRequest;
import com.github.register_service.request.RegisterResponse;
import com.github.register_service.request.UserResponseDTO;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;


   public RegisterResponse registerUser(RegisterRequest request) {

    Rol rol = rolRepository.findById(2L)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

    User user = User.builder()
            .email(request.getEmail())
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .rol(rol)
            .build();

    userRepository.save(user);

    return RegisterResponse.builder()
            .message("Usuario registrado correctamente")
            .status(201).count(1)
            .build();
}


    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }


    public UserResponseDTO getUserByUsername(String username) {
    User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

    UserResponseDTO dto = new UserResponseDTO();
    dto.setUsername(user.getUsername());
    dto.setPassword(user.getPassword());
    dto.setRole(user.getRol().getNombre()); 

    return dto;
}


    

}
