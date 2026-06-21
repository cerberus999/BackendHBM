package com.hirsoshia.motors.api.model.clientela;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "clientela", name = "solicitudes_repuesto")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SolicitudRepuesto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad", nullable = false)
    private UnidadCliente unidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_repuesto")
    private Repuesto repuesto;

    @Column(length = 500)
    private String descripcionLibre;

    @Column(nullable = false)
    @Builder.Default
    private Integer cantidadSolicitada = 1;

    @Column(name = "fecha_solicitud")
    @Builder.Default
    private LocalDateTime fechaSolicitud = LocalDateTime.now();

    @Column(length = 30)
    @Builder.Default
    private String estadoSolicitud = "pendiente";

    private LocalDate fechaDisponibilidad;

    @Builder.Default
    private Boolean notificadoCliente = false;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioCotizado;

    @Column(length = 500)
    private String notasTaller;
}
