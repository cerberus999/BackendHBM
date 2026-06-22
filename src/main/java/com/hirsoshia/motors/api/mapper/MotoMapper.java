package com.hirsoshia.motors.api.mapper;

import com.hirsoshia.motors.api.dto.response.MotoResponse;
import com.hirsoshia.motors.api.dto.response.MotoSpecs;
import com.hirsoshia.motors.api.model.ventas.Moto;
import org.springframework.stereotype.Component;

@Component
public class MotoMapper implements DtoMapper<Moto, MotoResponse> {

    @Override
    public MotoResponse toDto(Moto moto) {
        return new MotoResponse(
                moto.getIdMoto(),
                moto.getDistribuidor().getNombre(),
                moto.getDistribuidor().getIdDistribuidor(),
                moto.getMarca(),
                moto.getModelo(),
                moto.getAño(),
                moto.getTipo(),
                moto.getCilindradaCc(),
                moto.getPrecioImportacion(),
                moto.getPrecioVenta(),
                moto.getStock(),
                moto.getColor(),
                moto.getImagenUrl(),
                moto.getEstado(),
                new MotoSpecs(
                        moto.getRefrigeracion(),
                        moto.getPotenciaCv(),
                        moto.getPotenciaRpm(),
                        moto.getTorqueNm(),
                        moto.getTorqueRpm(),
                        moto.getAlimentacion(),
                        moto.getTransmision(),
                        moto.getVelocidadMaxKmh(),
                        moto.getDiametroCarrera(),
                        moto.getRelacionCompresion(),
                        moto.getPotenciaRamAirCv(),
                        moto.getArranque(),
                        moto.getChasisTipo(),
                        moto.getSuspensionDelantera(),
                        moto.getSuspensionTrasera(),
                        moto.getAmortiguadorDireccion(),
                        moto.getFrenoDelantero(),
                        moto.getFrenoTrasero(),
                        moto.getFrenoAsistencia(),
                        moto.getNeumaticoDelantero(),
                        moto.getNeumaticoTrasero(),
                        moto.getPesoKg(),
                        moto.getPesoSecoKg(),
                        moto.getPesoMarchaKg(),
                        moto.getDepositoLitros(),
                        moto.getAlturaAsientoMm(),
                        moto.getDistanciaEjesMm(),
                        moto.getDistanciaSueloMm(),
                        moto.getMaleteroLitros(),
                        moto.getElectronica()
                )
        );
    }
}
