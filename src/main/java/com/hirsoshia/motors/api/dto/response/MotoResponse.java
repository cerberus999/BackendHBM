package com.hirsoshia.motors.api.dto.response;

import java.math.BigDecimal;

public record MotoResponse(
        Long idMoto,
        String distribuidorNombre,
        Long idDistribuidor,
        String marca,
        String modelo,
        Integer año,
        String tipo,
        Integer cilindradaCc,
        BigDecimal precioImportacion,
        BigDecimal precioVenta,
        Integer stock,
        String color,
        String imagenUrl,
        String estado,
        MotoSpecs especificaciones
) {}
