package com.hirsoshia.motors.api.model.ventas;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "ventas", name = "distribuidores")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Distribuidor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDistribuidor;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 30)
    private String tipoRelacion;

    @Column(nullable = false, length = 100)
    private String paisOrigen;

    @Column(length = 100)
    private String contactoEmail;

    @Column(length = 20)
    private String telefono;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
}
