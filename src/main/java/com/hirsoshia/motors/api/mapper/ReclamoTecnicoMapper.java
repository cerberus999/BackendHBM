package com.hirsoshia.motors.api.mapper;

import com.hirsoshia.motors.api.dto.response.ReclamoTecnicoResponse;
import com.hirsoshia.motors.api.model.clientela.ReclamoTecnico;
import org.springframework.stereotype.Component;

@Component
public class ReclamoTecnicoMapper implements DtoMapper<ReclamoTecnico, ReclamoTecnicoResponse> {

    @Override
    public ReclamoTecnicoResponse toDto(ReclamoTecnico r) {
        return new ReclamoTecnicoResponse(
                r.getIdReclamoTec(),
                r.getUnidad().getIdUnidad(),
                r.getGarantiaPos() != null ? r.getGarantiaPos().getIdGarantiaPos() : null,
                r.getKilometrajeReclamo(),
                r.getFechaIngreso(),
                r.getDescripcionCliente(),
                r.getComponenteAfectado(),
                r.getDiagnosticoTecnico(),
                r.getCausaProbable(),
                r.getAplicaGarantia(),
                r.getAccionTomada(),
                r.getServicioRealizado() != null ? r.getServicioRealizado().getIdServicio() : null,
                r.getCostoReparacion(),
                r.getCostoCubiertoGarantia(),
                r.getCostoCobradoCliente(),
                r.getFechaResolucion(),
                r.getTecnicoResponsable(),
                r.getEstadoReclamo()
        );
    }
}
