package com.hirsoshia.motors.api.model.clientela;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(schema = "clientela", name = "planes_mantenimiento")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PlanMantenimiento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlan;

    @Column(nullable = false, length = 100)
    private String nombrePlan;

    @Column(length = 50)
    private String marcaAplica;

    @Column(length = 50)
    private String tipoMotoAplica;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private Integer kmInicio;

    @Column(nullable = false)
    private Integer kmFin;

    @Builder.Default
    private Boolean esGratuito = false;

    @Builder.Default
    private Boolean incluyeCambioAceite = true;

    @Builder.Default
    private Boolean incluyeAjusteFrenos = true;

    @Builder.Default
    private Boolean incluyeRevisionCadena = true;

    @Builder.Default
    private Boolean incluyeRevisionMotor = true;

    @Builder.Default
    private Boolean incluyeRevisionElectrica = false;

    @Builder.Default
    private Boolean incluyeRevisionSuspension = false;

    @Column(precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal costoServicio = BigDecimal.ZERO;

    @Column(precision = 4, scale = 2)
    private BigDecimal duracionEstimadaHoras;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
}
