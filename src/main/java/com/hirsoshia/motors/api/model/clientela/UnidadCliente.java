package com.hirsoshia.motors.api.model.clientela;

import com.hirsoshia.motors.api.model.ventas.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(schema = "clientela", name = "unidades_cliente")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UnidadCliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnidad;

    @Column(name = "id_cliente_ref", nullable = false)
    private Long idClienteRef;

    @Column(name = "id_venta_ref", nullable = false, unique = true)
    private Long idVentaRef;

    @Column(name = "id_moto_ref", nullable = false)
    private Long idMotoRef;

    @Column(nullable = false, unique = true, length = 50)
    private String numeroSerieMoto;

    @Column(nullable = false)
    private LocalDate fechaCompra;

    @Builder.Default
    private Integer kilometrajeCompra = 0;

    @Builder.Default
    private Integer kilometrajeActual = 0;

    @Column(length = 50)
    private String color;

    @Column(length = 20)
    private String placaPatente;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
}
