package com.github.coffee_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Standard error response")
public class ErrorResponseDto {

    @Schema(example = "Coffee not found with id: 5")
    private String message;

    @Schema(example = "404")
    private int status;

    @Schema(example = "1732819746000")
    private long timestamp;

    @Schema(example = "/api-v1/coffee/5")
    private String path;
}
