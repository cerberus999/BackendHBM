package com.hirsoshia.motors.api.dto.response;

import java.math.BigDecimal;

public record MotoSpecs(
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
