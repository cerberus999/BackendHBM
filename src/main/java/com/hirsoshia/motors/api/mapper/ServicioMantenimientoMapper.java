package com.hirsoshia.motors.api.mapper;

import com.hirsoshia.motors.api.dto.response.ServicioMantenimientoResponse;
import com.hirsoshia.motors.api.model.clientela.ServicioMantenimiento;
import org.springframework.stereotype.Component;

@Component
public class ServicioMantenimientoMapper implements DtoMapper<ServicioMantenimiento, ServicioMantenimientoResponse> {

    @Override
    public ServicioMantenimientoResponse toDto(ServicioMantenimiento s) {
        return new ServicioMantenimientoResponse(
                s.getIdServicio(),
                s.getUnidad().getIdUnidad(),
                s.getPlan() != null ? s.getPlan().getIdPlan() : null,
                s.getPlan() != null ? s.getPlan().getNombrePlan() : null,
                s.getFechaServicio(),
                s.getKilometrajeEntrada(),
                s.getTipoServicio(),
                s.getDescripcionTrabajos(),
                s.getAceiteCambiado(),
                s.getTipoAceiteUsado(),
                s.getFrenosAjustados(),
                s.getCadenaRevisada(),
                s.getMotorRevisado(),
                s.getElectricoRevisado(),
                s.getSuspensionRevisada(),
                s.getObservacionesTecnicas(),
                s.getCostoManoObra(),
                s.getCostoRepuestos(),
                s.getCostoTotal(),
                s.getEsGratuito(),
                s.getProximoServicioKm(),
                s.getProximoServicioFecha(),
                s.getTecnicoResponsable(),
                s.getEstadoServicio()
        );
    }
}
