package com.hirsoshia.motors.api.mapper;

import com.hirsoshia.motors.api.dto.response.SolicitudRepuestoResponse;
import com.hirsoshia.motors.api.model.clientela.SolicitudRepuesto;
import org.springframework.stereotype.Component;

@Component
public class SolicitudRepuestoMapper implements DtoMapper<SolicitudRepuesto, SolicitudRepuestoResponse> {

    @Override
    public SolicitudRepuestoResponse toDto(SolicitudRepuesto s) {
        return new SolicitudRepuestoResponse(
                s.getIdSolicitud(),
                s.getUnidad().getIdUnidad(),
                s.getRepuesto() != null ? s.getRepuesto().getIdRepuesto() : null,
                s.getRepuesto() != null ? s.getRepuesto().getNombre() : null,
                s.getRepuesto() != null ? s.getRepuesto().getCodigoRepuesto() : null,
                s.getDescripcionLibre(),
                s.getCantidadSolicitada(),
                s.getFechaSolicitud(),
                s.getEstadoSolicitud(),
                s.getFechaDisponibilidad(),
                s.getNotificadoCliente(),
                s.getPrecioCotizado(),
                s.getNotasTaller()
        );
    }
}
