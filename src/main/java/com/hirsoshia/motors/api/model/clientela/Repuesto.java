package com.hirsoshia.motors.api.model.clientela;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(schema = "clientela", name = "repuestos")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Repuesto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRepuesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaRepuesto categoria;

    @Column(nullable = false, unique = true, length = 50)
    private String codigoRepuesto;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(length = 50)
    private String marcaCompatible;

    @Column(length = 500)
    private String modelosCompatibles;

    @Column(name = "\"año_desde\"")
    private Integer añoDesde;

    @Column(name = "\"año_hasta\"")
    private Integer añoHasta;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioCosto;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVenta;

    @Column(nullable = false)
    @Builder.Default
    private Integer stockActual = 0;

    @Builder.Default
    private Integer stockMinimo = 2;

    @Column(length = 30)
    private String unidadMedida;

    @Column(columnDefinition = "text")
    private String imagenUrl;

    @Builder.Default
    private Boolean esRepuestoGarantia = false;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
}
