package com.hirsoshia.motors.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ServicioMantenimientoResponse(
        Long idServicio,
        Long idUnidad,
        Long idPlan,
        String nombrePlan,
        LocalDateTime fechaServicio,
        Integer kilometrajeEntrada,
        String tipoServicio,
        String descripcionTrabajos,
        Boolean aceiteCambiado,
        String tipoAceiteUsado,
        Boolean frenosAjustados,
        Boolean cadenaRevisada,
        Boolean motorRevisado,
        Boolean electricoRevisado,
        Boolean suspensionRevisada,
        String observacionesTecnicas,
        BigDecimal costoManoObra,
        BigDecimal costoRepuestos,
        BigDecimal costoTotal,
        Boolean esGratuito,
        Integer proximoServicioKm,
        LocalDate proximoServicioFecha,
        String tecnicoResponsable,
        String estadoServicio
) {}
