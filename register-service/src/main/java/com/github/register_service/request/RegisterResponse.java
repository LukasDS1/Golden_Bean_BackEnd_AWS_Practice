package com.github.register_service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Respuesta del sistema tras completar el proceso de registro.")
public class RegisterResponse {

    @Schema(description = "Mensaje descriptivo del resultado.", example = "Usuario registrado correctamente.")
    private String message;

    @Schema(description = "Código de estado asociado al resultado.", example = "201")
    private Integer status;

    @Schema(description = "Cantidad de registros afectados o creados.", example = "1")
    private Integer count;

}
