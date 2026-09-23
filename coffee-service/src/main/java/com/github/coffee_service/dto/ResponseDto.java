package com.github.coffee_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ResponseDto", description = "Standard API response wrapper containing metadata and data")
public class ResponseDto<T> {

    @Schema(description = "Message describing the result", example = "Coffees fetched successfully")
    private String message;

    @Schema(description = "Internal or HTTP status code", example = "200")
    private Integer status;

    @Schema(
        description = "Payload returned by the API (can be an object or a list)",
        implementation = Object.class
    )
    private T data;

    @Schema(description = "Total number of elements returned (if applicable)", example = "3")
    private Integer count;

    public ResponseDto(String message, Integer status, Integer count) {
        this.message = message;
        this.status = status;
        this.count = count;
    }
}