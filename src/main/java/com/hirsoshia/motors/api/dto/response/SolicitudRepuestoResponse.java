package com.hirsoshia.motors.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record SolicitudRepuestoResponse(
        Long idSolicitud,
        Long idUnidad,
        Long idRepuesto,
        String nombreRepuesto,
        String codigoRepuesto,
        String descripcionLibre,
        Integer cantidadSolicitada,
        LocalDateTime fechaSolicitud,
        String estadoSolicitud,
        LocalDate fechaDisponibilidad,
        Boolean notificadoCliente,
        BigDecimal precioCotizado,
        String notasTaller
) {}
