package com.hirsoshia.motors.api.model.clientela;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "clientela", name = "usuarios")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, columnDefinition = "text")
    private String passwordHash;

    @Column(nullable = false, length = 20)
    private String rol;

    @Column(name = "id_gerente")
    private Long idGerente;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
}
