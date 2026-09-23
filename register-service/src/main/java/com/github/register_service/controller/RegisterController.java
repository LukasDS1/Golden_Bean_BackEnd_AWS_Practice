package com.github.register_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.register_service.request.RegisterRequest;
import com.github.register_service.request.RegisterResponse;
import com.github.register_service.request.UserResponseDTO;
import com.github.register_service.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api-v1/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;


    @PostMapping
    @Operation(summary = "Este endpoint permite registrar un nuevo usuario en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "201",
                description = "CREATED: indica que el usuario fue registrado correctamente.",
                content = @Content(schema = @Schema(implementation = RegisterResponse.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "BAD REQUEST: la solicitud no es válida o faltan datos.",
                content = @Content(schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                responseCode = "409",
                description = "CONFLICT: el correo o username ya existen en el sistema.",
                content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = userService.registerUser(request);
        return ResponseEntity.status(201).body(response); 
    }

    @GetMapping("/{username}")
    @Operation(summary = "Obtiene información pública de un usuario a partir de su nombre de usuario.")
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "200",
                description = "OK: el usuario fue encontrado exitosamente.",
                content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "NOT FOUND: no se encontró un usuario con ese username.",
                content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    public UserResponseDTO getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

}
