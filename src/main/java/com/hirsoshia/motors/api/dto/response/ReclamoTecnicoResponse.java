package com.hirsoshia.motors.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReclamoTecnicoResponse(
        Long idReclamoTec,
        Long idUnidad,
        Long idGarantiaPos,
        Integer kilometrajeReclamo,
        LocalDateTime fechaIngreso,
        String descripcionCliente,
        String componenteAfectado,
        String diagnosticoTecnico,
        String causaProbable,
        Boolean aplicaGarantia,
        String accionTomada,
        Long idServicioRealizado,
        BigDecimal costoReparacion,
        BigDecimal costoCubiertoGarantia,
        BigDecimal costoCobradoCliente,
        LocalDate fechaResolucion,
        String tecnicoResponsable,
        String estadoReclamo
) {}
