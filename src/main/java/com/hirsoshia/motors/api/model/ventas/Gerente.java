package com.hirsoshia.motors.api.model.ventas;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(schema = "ventas", name = "gerentes")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Gerente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGerente;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(length = 100)
    private String cargo;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "fecha_ingreso")
    @Builder.Default
    private LocalDate fechaIngreso = LocalDate.now();
}
