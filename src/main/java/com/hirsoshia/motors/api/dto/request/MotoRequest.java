package com.hirsoshia.motors.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record MotoRequest(
        @NotNull Long idDistribuidor,
        @NotBlank String marca,
        @NotBlank String modelo,
        @NotNull Integer año,
        String tipo,
        Integer cilindradaCc,
        @NotNull @Positive BigDecimal precioImportacion,
        @NotNull @Positive BigDecimal precioVenta,
        @NotNull @Positive Integer stock,
        String color,
        String imagenUrl,
        String estado,
        String refrigeracion,
        BigDecimal potenciaCv,
        Integer potenciaRpm,
        BigDecimal torqueNm,
        Integer torqueRpm,
        String alimentacion,
        String transmision,
        Integer velocidadMaxKmh,
        String diametroCarrera,
        String relacionCompresion,
        Integer potenciaRamAirCv,
        String arranque,
        String chasisTipo,
        String suspensionDelantera,
        String suspensionTrasera,
        String amortiguadorDireccion,
        String frenoDelantero,
        String frenoTrasero,
        String frenoAsistencia,
        String neumaticoDelantero,
        String neumaticoTrasero,
        BigDecimal pesoKg,
        BigDecimal pesoSecoKg,
        BigDecimal pesoMarchaKg,
        BigDecimal depositoLitros,
        Integer alturaAsientoMm,
        Integer distanciaEjesMm,
        Integer distanciaSueloMm,
        Integer maleteroLitros,
        String electronica
) {}
