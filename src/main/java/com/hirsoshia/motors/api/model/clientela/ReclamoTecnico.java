package com.hirsoshia.motors.api.model.clientela;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "clientela", name = "reclamos_tecnicos")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ReclamoTecnico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReclamoTec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad", nullable = false)
    private UnidadCliente unidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_garantia_pos")
    private GarantiaPosventa garantiaPos;

    @Column(nullable = false)
    private Integer kilometrajeReclamo;

    @Column(name = "fecha_ingreso")
    @Builder.Default
    private LocalDateTime fechaIngreso = LocalDateTime.now();

    @Column(nullable = false, length = 500)
    private String descripcionCliente;

    @Column(length = 100)
    private String componenteAfectado;

    @Column(length = 500)
    private String diagnosticoTecnico;

    @Column(length = 500)
    private String causaProbable;

    @Builder.Default
    private Boolean aplicaGarantia = false;

    @Column(length = 50)
    private String accionTomada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servicio_realizado")
    private ServicioMantenimiento servicioRealizado;

    @Column(precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal costoReparacion = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal costoCubiertoGarantia = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal costoCobradoCliente = BigDecimal.ZERO;

    private LocalDate fechaResolucion;

    @Column(length = 100)
    private String tecnicoResponsable;

    @Column(length = 30)
    @Builder.Default
    private String estadoReclamo = "abierto";

    @Column(length = 500)
    private String notasInternas;

    @Builder.Default
    private Boolean requiereRepuestoImportado = false;
}
