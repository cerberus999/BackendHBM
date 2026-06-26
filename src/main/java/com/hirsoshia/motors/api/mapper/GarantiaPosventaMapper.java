package com.hirsoshia.motors.api.mapper;

import com.hirsoshia.motors.api.dto.response.GarantiaPosventaResponse;
import com.hirsoshia.motors.api.model.clientela.GarantiaPosventa;
import org.springframework.stereotype.Component;

@Component
public class GarantiaPosventaMapper implements DtoMapper<GarantiaPosventa, GarantiaPosventaResponse> {

    @Override
    public GarantiaPosventaResponse toDto(GarantiaPosventa g) {
        return new GarantiaPosventaResponse(
                g.getIdGarantiaPos(),
                g.getUnidad().getIdUnidad(),
                g.getTipoGarantiaNombre(),
                g.getFechaInicio(),
                g.getFechaFin(),
                g.getCubreMotor(),
                g.getCubreTransmision(),
                g.getCubreElectrico(),
                g.getCubreCarroceria(),
                g.getEstadoGarantia(),
                g.getKilometrajeMaximoGarantia(),
                g.getCondicionesEspeciales()
        );
    }
}
