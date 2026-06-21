package com.hirsoshia.motors.api.model.clientela;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "clientela", name = "alertas_servicio")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AlertaServicio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlerta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad", nullable = false)
    private UnidadCliente unidad;

    @Column(nullable = false, length = 80)
    private String tipoAlerta;

    @Column(nullable = false, length = 500)
    private String mensaje;

    @Column(name = "fecha_generacion")
    @Builder.Default
    private LocalDateTime fechaGeneracion = LocalDateTime.now();

    private LocalDateTime fechaEnvio;

    @Column(length = 50)
    private String canalEnvio;

    @Column(length = 30)
    @Builder.Default
    private String estadoAlerta = "pendiente";

    private Integer kmReferencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud_repuesto")
    private SolicitudRepuesto solicitudRepuesto;
}
