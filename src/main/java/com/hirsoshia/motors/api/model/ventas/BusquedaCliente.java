package com.hirsoshia.motors.api.model.ventas;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "ventas", name = "busquedas_clientes")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BusquedaCliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBusqueda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_moto")
    private Moto moto;

    @Column(name = "fecha_busqueda")
    @Builder.Default
    private LocalDateTime fechaBusqueda = LocalDateTime.now();

    @Column(length = 50)
    private String marcaBuscada;

    @Column(length = 100)
    private String modeloBuscado;

    @Column(length = 50)
    private String tipoBuscado;
}
