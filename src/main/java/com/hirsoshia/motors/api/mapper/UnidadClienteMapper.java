package com.hirsoshia.motors.api.mapper;

import com.hirsoshia.motors.api.dto.response.UnidadClienteResponse;
import com.hirsoshia.motors.api.model.clientela.UnidadCliente;
import org.springframework.stereotype.Component;

@Component
public class UnidadClienteMapper implements DtoMapper<UnidadCliente, UnidadClienteResponse> {

    @Override
    public UnidadClienteResponse toDto(UnidadCliente uc) {
        return new UnidadClienteResponse(
                uc.getIdUnidad(),
                uc.getIdClienteRef(),
                uc.getIdVentaRef(),
                uc.getIdMotoRef(),
                uc.getNumeroSerieMoto(),
                uc.getFechaCompra(),
                uc.getKilometrajeCompra(),
                uc.getKilometrajeActual(),
                uc.getColor(),
                uc.getPlacaPatente(),
                uc.getActivo());
    }
}
