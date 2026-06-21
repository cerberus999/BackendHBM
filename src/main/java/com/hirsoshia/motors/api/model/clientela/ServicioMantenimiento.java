package com.hirsoshia.motors.api.model.clientela;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "clientela", name = "servicios_mantenimiento")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServicioMantenimiento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad", nullable = false)
    private UnidadCliente unidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan")
    private PlanMantenimiento plan;

    @Column(nullable = false)
    private LocalDateTime fechaServicio;

    @Column(nullable = false)
    private Integer kilometrajeEntrada;

    @Column(nullable = false, length = 50)
    private String tipoServicio;

    @Column(nullable = false, length = 500)
    private String descripcionTrabajos;

    @Builder.Default
    private Boolean aceiteCambiado = false;

    @Column(length = 80)
    private String tipoAceiteUsado;

    @Builder.Default
    private Boolean frenosAjustados = false;

    @Builder.Default
    private Boolean cadenaRevisada = false;

    @Builder.Default
    private Boolean motorRevisado = false;

    @Builder.Default
    private Boolean electricoRevisado = false;

    @Builder.Default
    private Boolean suspensionRevisada = false;

    @Column(length = 500)
    private String observacionesTecnicas;

    @Column(precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal costoManoObra = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal costoRepuestos = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal costoTotal = BigDecimal.ZERO;

    @Builder.Default
    private Boolean esGratuito = false;

    private Integer proximoServicioKm;

    private LocalDate proximoServicioFecha;

    @Column(nullable = false, length = 100)
    private String tecnicoResponsable;

    @Column(name = "id_vendedor_ref")
    private Long idVendedorRef;

    @Column(length = 30)
    @Builder.Default
    private String estadoServicio = "completado";
}
