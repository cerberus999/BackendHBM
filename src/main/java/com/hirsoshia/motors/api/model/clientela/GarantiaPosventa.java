package com.hirsoshia.motors.api.model.clientela;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(schema = "clientela", name = "garantias_posventa")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class GarantiaPosventa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGarantiaPos;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad", nullable = false, unique = true)
    private UnidadCliente unidad;

    @Column(nullable = false, length = 100)
    private String tipoGarantiaNombre;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @Builder.Default
    private Boolean cubreMotor = true;

    @Builder.Default
    private Boolean cubreTransmision = true;

    @Builder.Default
    private Boolean cubreElectrico = false;

    @Builder.Default
    private Boolean cubreCarroceria = false;

    @Column(length = 30)
    @Builder.Default
    private String estadoGarantia = "activa";

    private Integer kilometrajeMaximoGarantia;

    @Column(length = 500)
    private String condicionesEspeciales;
}
