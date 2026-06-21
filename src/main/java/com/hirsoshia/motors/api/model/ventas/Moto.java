package com.hirsoshia.motors.api.model.ventas;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(schema = "ventas", name = "motos")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Moto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMoto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_distribuidor", nullable = false)
    private Distribuidor distribuidor;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 100)
    private String modelo;

    @Column(name = "\"año\"", nullable = false)
    private Integer año;

    @Column(length = 50)
    private String tipo;

    @Column(name = "cilindrada_cc")
    private Integer cilindradaCc;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precioImportacion;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precioVenta;

    @Column(nullable = false)
    @Builder.Default
    private Integer stock = 0;

    @Column(length = 50)
    private String color;

    @Column(columnDefinition = "text")
    private String imagenUrl;

    @Column(length = 30)
    @Builder.Default
    private String estado = "disponible";
}
