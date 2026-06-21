package com.hirsoshia.motors.api.model.ventas;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(schema = "ventas", name = "ventas")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Venta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gerente", nullable = false)
    private Gerente gerente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_moto", nullable = false)
    private Moto moto;

    @Column(name = "fecha_venta")
    @Builder.Default
    private LocalDateTime fechaVenta = LocalDateTime.now();

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precioFinal;

    @Column(precision = 5, scale = 2)
    @Builder.Default
    private BigDecimal descuentoAplicado = BigDecimal.ZERO;

    @Column(length = 30)
    private String metodoPago;

    @Column(length = 500)
    private String notas;
}
