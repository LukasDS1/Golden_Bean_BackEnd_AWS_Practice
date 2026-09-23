package com.github.register_service.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Entidad que representa un usuario del sistema.")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del usuario.", example = "1")
    private Long userId;

    @Column(nullable = false, unique = true)
    @Schema(description = "Correo electrónico del usuario, utilizado para autenticación.", example = "usuario@example.com")
    private String email;

    @Column(nullable = false)
    @Schema(description = "Contraseña cifrada del usuario.", example = "$2a$10$abcd1234")
    private String password;

    @Column(nullable = false, unique = true)
    @Schema(description = "Nombre de usuario visible en el sistema.", example = "juan_perez")
    private String username;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol")
    @JsonIgnoreProperties("users")
    @Schema(description = "Rol asignado al usuario.")
    private Rol rol;
}

    
