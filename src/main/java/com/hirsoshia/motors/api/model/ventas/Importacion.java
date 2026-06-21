package com.hirsoshia.motors.api.model.ventas;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(schema = "ventas", name = "importaciones")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Importacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImportacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_moto", nullable = false)
    private Moto moto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_distribuidor", nullable = false)
    private Distribuidor distribuidor;

    @Column(length = 50, unique = true)
    private String numeroPedido;

    @Column(nullable = false)
    private LocalDate fechaPedido;

    private LocalDate fechaEstimadaLlegada;

    private LocalDate fechaLlegadaReal;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(precision = 12, scale = 2)
    private BigDecimal costoUnitarioUsd;

    @Column(precision = 14, scale = 2)
    private BigDecimal costoTotalUsd;

    @Column(length = 30)
    private String estadoEnvio;

    @Column(length = 500)
    private String notas;
}
