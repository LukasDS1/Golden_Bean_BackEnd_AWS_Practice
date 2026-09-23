package com.github.register_service.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rol")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un rol del sistema.")
public class Rol { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del rol.", example = "1")
    private Long idRol;

    @Column(nullable = false, length = 20)
    @Schema(description = "Nombre del rol.", example = "ADMIN")
    private String nombre;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    @JsonIgnore
    @Schema(description = "Lista de usuarios asociados a este rol. No se expone en las respuestas.")
    private List<User> users;
}
