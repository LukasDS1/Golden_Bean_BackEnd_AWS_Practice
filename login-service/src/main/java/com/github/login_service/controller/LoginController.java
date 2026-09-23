package com.github.login_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.login_service.request.AuthResponse;
import com.github.login_service.request.LoginRequest;
import com.github.login_service.service.LoginService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api-v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    @Operation(summary = "Este endpoint permite autenticar a un usuario y generar un token JWT válido.")
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "200",
                description = "OK: el usuario fue autenticado correctamente.",
                content = @Content(schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "BAD REQUEST: la solicitud no es válida o faltan datos para iniciar sesión.",
                content = @Content(schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                responseCode = "401",
                description = "UNAUTHORIZED: las credenciales proporcionadas son incorrectas.",
                content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginService.login(request));
    }
}
