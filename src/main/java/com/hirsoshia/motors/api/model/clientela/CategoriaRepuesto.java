package com.hirsoshia.motors.api.model.clientela;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "clientela", name = "categorias_repuesto")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CategoriaRepuesto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @Column(nullable = false, length = 80)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
}
