package com.github.register_service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO que representa la información pública de un usuario.")
public class UserResponseDTO {

    @Schema(description = "Nombre de usuario.", example = "maria_gonzalez")
    private String username;

    @Schema(description = "Contraseña en texto plano o codificada según contexto.", example = "$2a$10$abcd1234")
    private String password;

    @Schema(description = "Rol asignado al usuario.", example = "USER")
    private String role;
}
