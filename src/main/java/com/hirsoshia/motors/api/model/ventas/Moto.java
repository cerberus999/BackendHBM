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

    @Column(length = 50)
    private String refrigeracion;

    @Column(name = "potencia_cv", precision = 5, scale = 1)
    private BigDecimal potenciaCv;

    @Column(name = "potencia_rpm")
    private Integer potenciaRpm;

    @Column(name = "torque_nm", precision = 5, scale = 1)
    private BigDecimal torqueNm;

    @Column(name = "torque_rpm")
    private Integer torqueRpm;

    @Column(length = 100)
    private String alimentacion;

    @Column(length = 150)
    private String transmision;

    @Column(name = "velocidad_max_kmh")
    private Integer velocidadMaxKmh;

    @Column(name = "diametro_carrera", length = 50)
    private String diametroCarrera;

    @Column(name = "relacion_compresion", length = 20)
    private String relacionCompresion;

    @Column(name = "potencia_ram_air_cv")
    private Integer potenciaRamAirCv;

    @Column(length = 30)
    private String arranque;

    @Column(name = "chasis_tipo", length = 100)
    private String chasisTipo;

    @Column(name = "suspension_delantera", length = 150)
    private String suspensionDelantera;

    @Column(name = "suspension_trasera", length = 150)
    private String suspensionTrasera;

    @Column(name = "amortiguador_direccion", length = 100)
    private String amortiguadorDireccion;

    @Column(name = "freno_delantero", length = 200)
    private String frenoDelantero;

    @Column(name = "freno_trasero", length = 200)
    private String frenoTrasero;

    @Column(name = "freno_asistencia", length = 50)
    private String frenoAsistencia;

    @Column(name = "neumatico_delantero", length = 60)
    private String neumaticoDelantero;

    @Column(name = "neumatico_trasero", length = 60)
    private String neumaticoTrasero;

    @Column(name = "peso_kg", precision = 6, scale = 1)
    private BigDecimal pesoKg;

    @Column(name = "peso_seco_kg", precision = 6, scale = 1)
    private BigDecimal pesoSecoKg;

    @Column(name = "peso_marcha_kg", precision = 6, scale = 1)
    private BigDecimal pesoMarchaKg;

    @Column(name = "deposito_litros", precision = 4, scale = 1)
    private BigDecimal depositoLitros;

    @Column(name = "altura_asiento_mm")
    private Integer alturaAsientoMm;

    @Column(name = "distancia_ejes_mm")
    private Integer distanciaEjesMm;

    @Column(name = "distancia_suelo_mm")
    private Integer distanciaSueloMm;

    @Column(name = "maletero_litros")
    private Integer maleteroLitros;

    @Column(columnDefinition = "text")
    private String electronica;
}
