package com.hirsoshia.motors.api.dto.response;

import java.time.LocalDate;

public record GarantiaPosventaResponse(
        Long idGarantiaPos,
        Long idUnidad,
        String tipoGarantiaNombre,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        Boolean cubreMotor,
        Boolean cubreTransmision,
        Boolean cubreElectrico,
        Boolean cubreCarroceria,
        String estadoGarantia,
        Integer kilometrajeMaximoGarantia,
        String condicionesEspeciales
) {}
