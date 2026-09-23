package com.github.register_service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request para registrar un nuevo usuario en el sistema.")
public class RegisterRequest {
    
    @Schema(description = "Nombre de usuario solicitado.", example = "jhon_doe")
    private String username;

    @Schema(description = "Correo electrónico del usuario.", example = "jhon.doe@example.com")
    private String email;

    @Schema(description = "Contraseña del usuario sin cifrar, enviada para registro.", example = "P4ssw0rd!")
    private String password;

}
