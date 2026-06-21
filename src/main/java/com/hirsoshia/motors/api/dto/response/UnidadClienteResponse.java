package com.hirsoshia.motors.api.dto.response;

import java.time.LocalDate;

public record UnidadClienteResponse(
        Long idUnidad,
        Long idClienteRef,
        Long idVentaRef,
        Long idMotoRef,
        String numeroSerieMoto,
        LocalDate fechaCompra,
        Integer kilometrajeCompra,
        Integer kilometrajeActual,
        String color,
        String placaPatente,
        Boolean activo
) {}
