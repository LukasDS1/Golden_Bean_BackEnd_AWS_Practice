package com.github.coffee_service.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coffe")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Coffee", description = "Represents a coffee product in the system")
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the coffee", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Display name of the coffee", example = "Espresso")
    private String nombre;

    @Column(nullable = false)
    @Schema(description = "Normalized name used for search operations", example = "espresso")
    private String nombreNormalizado;

    @Column(nullable = false)
    @Schema(description = "Image URL of the coffee", example = "https://cdn.example.com/images/espresso.jpg")
    private String imagen;

    @Column(nullable = true)
    @Schema(description = "Optional description of the coffee", example = "Medium roast with chocolate notes")
    private String descripcion;
    
    @Column(nullable = false)
    @Schema(description = "Price in Chilean pesos (CLP)", example = "5500")
    private Integer precioCLP;
}
