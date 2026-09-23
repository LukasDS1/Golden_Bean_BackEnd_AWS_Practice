package com.github.coffee_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.coffee_service.dto.ResponseDto;
import com.github.coffee_service.model.Coffee;
import com.github.coffee_service.service.CoffeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api-v1/coffee")
@RequiredArgsConstructor
public class CoffeeController {

    private final CoffeeService coffeeService;

    @Operation(
        summary = "Get all coffees",
        description = "Returns a complete list of coffees.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "List returned successfully",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseDto.class)
                )
            )
        }
    )
    @GetMapping
    public ResponseEntity<ResponseDto<List<Coffee>>> getAll() {
        List<Coffee> coffees = coffeeService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseDto<>(
                "Coffees fetched correctly",
                HttpStatus.OK.value(),
                coffees,
                coffees.size()
            )
        );
    }

    @Operation(
        summary = "Get a coffee by ID",
        description = "Fetch a coffee using its database ID.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Coffee found",
                content = @Content(schema = @Schema(implementation = ResponseDto.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Coffee not found",
                content = @Content(schema = @Schema(implementation = com.github.coffee_service.dto.ErrorResponseDto.class))
            )
        }
    )
    @GetMapping("/{idCoffee}")
    public ResponseEntity<ResponseDto<Coffee>> getById(@PathVariable Long idCoffee) {
        Coffee coffee = coffeeService.getById(idCoffee);

        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseDto<>(
                "Coffee fetched correctly.",
                HttpStatus.OK.value(),
                coffee,
                1
            )
        );
    }

    @Operation(
        summary = "Create a new coffee",
        description = "Adds a new coffee to the system.",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Coffee created successfully",
                content = @Content(schema = @Schema(implementation = ResponseDto.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid input data",
                content = @Content(schema = @Schema(implementation = com.github.coffee_service.dto.ErrorResponseDto.class))
            )
        }
    )
    @PostMapping
    public ResponseEntity<ResponseDto<Coffee>> create(@RequestBody Coffee newCoffee) {
        Coffee coffee = coffeeService.create(newCoffee);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new ResponseDto<>(
                "Coffee created correctly.",
                HttpStatus.CREATED.value(),
                coffee,
                1
            )
        );
        
    }

    @Operation(
        summary = "Update a coffee",
        description = "Updates an existing coffee using its ID.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Coffee updated successfully",
                content = @Content(schema = @Schema(implementation = ResponseDto.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Coffee not found",
                content = @Content(schema = @Schema(implementation = com.github.coffee_service.dto.ErrorResponseDto.class))
            )
        }
    )
    @PutMapping("/{idCoffee}")
    public ResponseEntity<ResponseDto<Coffee>> update(@PathVariable Long idCoffee, @RequestBody Coffee newCoffee) {
        Coffee coffee = coffeeService.update(idCoffee, newCoffee);

        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseDto<>(
                "Coffee updated correctly.",
                HttpStatus.OK.value(),
                coffee,
                1
            )
        );
    }

    @Operation(
        summary = "Search coffees by normalized name",
        description = """
            Normalizes the input and searches by:
            - lowercase  
            - trimming  
            - replacing spaces with underscores  
            - filtering only valid characters  
            Uses LIKE/CONTAINS for partial matches.
        """,
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Filtered list returned",
                content = @Content(schema = @Schema(implementation = ResponseDto.class))
            )
        }
    )
    @GetMapping("/findByNormalized")
    public ResponseEntity<ResponseDto<List<Coffee>>> getByNormalized(@RequestParam String nombreNormalizado) {
        List<Coffee> coffees = coffeeService.getByNormalized(nombreNormalizado);

        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseDto<>(
                "Coffees fetched correctly",
                HttpStatus.OK.value(),
                coffees,
                coffees.size()
            )
        );
    }

    @Operation(
        summary = "Delete a coffee",
        description = "Deletes a coffee using its ID.",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "Coffee deleted successfully"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Coffee not found",
                content = @Content(schema = @Schema(implementation = com.github.coffee_service.dto.ErrorResponseDto.class))
            )
        }
    )
    @DeleteMapping("/{idCoffee}")
    public ResponseEntity<ResponseDto<Void>> delete(@PathVariable Long idCoffee) {
        coffeeService.delete(idCoffee);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseDto<>(
                "Coffee deleted correctly.",
                HttpStatus.OK.value(),
                0
            )
        );
    }
    
    
}