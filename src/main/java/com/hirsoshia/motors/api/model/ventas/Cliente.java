package com.hirsoshia.motors.api.model.ventas;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(schema = "ventas", name = "clientes")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, length = 20, unique = true)
    private String ci;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 200)
    private String direccion;

    @Column(name = "fecha_registro")
    @Builder.Default
    private LocalDate fechaRegistro = LocalDate.now();
}
