package com.hirsoshia.motors.api.model.clientela;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(schema = "clientela", name = "repuestos_usados_en_servicio")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RepuestoUsadoEnServicio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsoRepuesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servicio", nullable = false)
    private ServicioMantenimiento servicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_repuesto", nullable = false)
    private Repuesto repuesto;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal cantidadUsada;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitarioCobrado;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Builder.Default
    private Boolean cubiertoPorGarantia = false;

    @Builder.Default
    private Boolean cubiertoPorMantenimientoGratis = false;
}
