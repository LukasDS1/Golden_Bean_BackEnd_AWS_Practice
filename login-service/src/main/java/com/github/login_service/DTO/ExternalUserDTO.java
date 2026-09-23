package com.github.login_service.DTO;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO que representa un usuario externo para registro o autenticación.")
public class ExternalUserDTO {

    @Schema(
        description = "Nombre de usuario del sistema.",
        example = "juan.perez"
    )
    private String username;

    @Schema(
        description = "Contraseña del usuario. Debe ser enviada de forma segura.",
        example = "P4ssw0rd!"
    )
    private String password;

    @Schema(
        description = "Rol asignado al usuario dentro del sistema.",
        example = "ADMIN"
    )
    private String role;
}
