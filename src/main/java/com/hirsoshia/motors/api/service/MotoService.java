package com.hirsoshia.motors.api.service;

import com.hirsoshia.motors.api.dto.request.MotoRequest;
import com.hirsoshia.motors.api.dto.response.MotoResponse;
import com.hirsoshia.motors.api.exception.ResourceNotFoundException;
import com.hirsoshia.motors.api.mapper.MotoMapper;
import com.hirsoshia.motors.api.model.ventas.Distribuidor;
import com.hirsoshia.motors.api.model.ventas.Moto;
import com.hirsoshia.motors.api.repository.ventas.DistribuidorRepository;
import com.hirsoshia.motors.api.repository.ventas.MotoRepository;
import com.hirsoshia.motors.api.specification.MotoSpecification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MotoService {

    private final MotoRepository motoRepository;
    private final DistribuidorRepository distribuidorRepository;
    private final MotoMapper motoMapper;

    public MotoService(MotoRepository motoRepository,
                       DistribuidorRepository distribuidorRepository,
                       MotoMapper motoMapper) {
        this.motoRepository = motoRepository;
        this.distribuidorRepository = distribuidorRepository;
        this.motoMapper = motoMapper;
    }

    public List<MotoResponse> listarTodas() {
        return motoRepository.findAll().stream().map(motoMapper::toDto).toList();
    }

    public List<MotoResponse> listarDisponibles() {
        return motoRepository.findByEstado("disponible").stream().map(motoMapper::toDto).toList();
    }

    public List<MotoResponse> buscar(String marca, String modelo, String tipo,
                                      BigDecimal precioMin, BigDecimal precioMax) {
        return motoRepository.findAll(MotoSpecification.conFiltros(marca, modelo, tipo, precioMin, precioMax))
                .stream().map(motoMapper::toDto).toList();
    }

    public MotoResponse obtenerPorId(Long id) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto no encontrada: " + id));
        return motoMapper.toDto(moto);
    }

    public MotoResponse crear(MotoRequest request) {
        Distribuidor distribuidor = distribuidorRepository.findById(request.idDistribuidor())
                .orElseThrow(() -> new ResourceNotFoundException("Distribuidor no encontrado"));

        Moto moto = Moto.builder()
                .distribuidor(distribuidor)
                .marca(request.marca())
                .modelo(request.modelo())
                .año(request.año())
                .tipo(request.tipo())
                .cilindradaCc(request.cilindradaCc())
                .precioImportacion(request.precioImportacion())
                .precioVenta(request.precioVenta())
                .stock(request.stock())
                .color(request.color())
                .imagenUrl(request.imagenUrl())
                .estado(request.estado() != null ? request.estado() : "disponible")
                .refrigeracion(request.refrigeracion())
                .potenciaCv(request.potenciaCv())
                .potenciaRpm(request.potenciaRpm())
                .torqueNm(request.torqueNm())
                .torqueRpm(request.torqueRpm())
                .alimentacion(request.alimentacion())
                .transmision(request.transmision())
                .velocidadMaxKmh(request.velocidadMaxKmh())
                .diametroCarrera(request.diametroCarrera())
                .relacionCompresion(request.relacionCompresion())
                .potenciaRamAirCv(request.potenciaRamAirCv())
                .arranque(request.arranque())
                .chasisTipo(request.chasisTipo())
                .suspensionDelantera(request.suspensionDelantera())
                .suspensionTrasera(request.suspensionTrasera())
                .amortiguadorDireccion(request.amortiguadorDireccion())
                .frenoDelantero(request.frenoDelantero())
                .frenoTrasero(request.frenoTrasero())
                .frenoAsistencia(request.frenoAsistencia())
                .neumaticoDelantero(request.neumaticoDelantero())
                .neumaticoTrasero(request.neumaticoTrasero())
                .pesoKg(request.pesoKg())
                .pesoSecoKg(request.pesoSecoKg())
                .pesoMarchaKg(request.pesoMarchaKg())
                .depositoLitros(request.depositoLitros())
                .alturaAsientoMm(request.alturaAsientoMm())
                .distanciaEjesMm(request.distanciaEjesMm())
                .distanciaSueloMm(request.distanciaSueloMm())
                .maleteroLitros(request.maleteroLitros())
                .electronica(request.electronica())
                .build();

        return motoMapper.toDto(motoRepository.save(moto));
    }

    public MotoResponse actualizar(Long id, MotoRequest request) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto no encontrada: " + id));

        Distribuidor distribuidor = distribuidorRepository.findById(request.idDistribuidor())
                .orElseThrow(() -> new ResourceNotFoundException("Distribuidor no encontrado"));

        moto.setDistribuidor(distribuidor);
        moto.setMarca(request.marca());
        moto.setModelo(request.modelo());
        moto.setAño(request.año());
        moto.setTipo(request.tipo());
        moto.setCilindradaCc(request.cilindradaCc());
        moto.setPrecioImportacion(request.precioImportacion());
        moto.setPrecioVenta(request.precioVenta());
        moto.setStock(request.stock());
        moto.setColor(request.color());
        moto.setImagenUrl(request.imagenUrl());
        if (request.estado() != null) moto.setEstado(request.estado());
        if (request.refrigeracion() != null) moto.setRefrigeracion(request.refrigeracion());
        if (request.potenciaCv() != null) moto.setPotenciaCv(request.potenciaCv());
        if (request.potenciaRpm() != null) moto.setPotenciaRpm(request.potenciaRpm());
        if (request.torqueNm() != null) moto.setTorqueNm(request.torqueNm());
        if (request.torqueRpm() != null) moto.setTorqueRpm(request.torqueRpm());
        if (request.alimentacion() != null) moto.setAlimentacion(request.alimentacion());
        if (request.transmision() != null) moto.setTransmision(request.transmision());
        if (request.velocidadMaxKmh() != null) moto.setVelocidadMaxKmh(request.velocidadMaxKmh());
        if (request.diametroCarrera() != null) moto.setDiametroCarrera(request.diametroCarrera());
        if (request.relacionCompresion() != null) moto.setRelacionCompresion(request.relacionCompresion());
        if (request.potenciaRamAirCv() != null) moto.setPotenciaRamAirCv(request.potenciaRamAirCv());
        if (request.arranque() != null) moto.setArranque(request.arranque());
        if (request.chasisTipo() != null) moto.setChasisTipo(request.chasisTipo());
        if (request.suspensionDelantera() != null) moto.setSuspensionDelantera(request.suspensionDelantera());
        if (request.suspensionTrasera() != null) moto.setSuspensionTrasera(request.suspensionTrasera());
        if (request.amortiguadorDireccion() != null) moto.setAmortiguadorDireccion(request.amortiguadorDireccion());
        if (request.frenoDelantero() != null) moto.setFrenoDelantero(request.frenoDelantero());
        if (request.frenoTrasero() != null) moto.setFrenoTrasero(request.frenoTrasero());
        if (request.frenoAsistencia() != null) moto.setFrenoAsistencia(request.frenoAsistencia());
        if (request.neumaticoDelantero() != null) moto.setNeumaticoDelantero(request.neumaticoDelantero());
        if (request.neumaticoTrasero() != null) moto.setNeumaticoTrasero(request.neumaticoTrasero());
        if (request.pesoKg() != null) moto.setPesoKg(request.pesoKg());
        if (request.pesoSecoKg() != null) moto.setPesoSecoKg(request.pesoSecoKg());
        if (request.pesoMarchaKg() != null) moto.setPesoMarchaKg(request.pesoMarchaKg());
        if (request.depositoLitros() != null) moto.setDepositoLitros(request.depositoLitros());
        if (request.alturaAsientoMm() != null) moto.setAlturaAsientoMm(request.alturaAsientoMm());
        if (request.distanciaEjesMm() != null) moto.setDistanciaEjesMm(request.distanciaEjesMm());
        if (request.distanciaSueloMm() != null) moto.setDistanciaSueloMm(request.distanciaSueloMm());
        if (request.maleteroLitros() != null) moto.setMaleteroLitros(request.maleteroLitros());
        if (request.electronica() != null) moto.setElectronica(request.electronica());

        return motoMapper.toDto(motoRepository.save(moto));
    }

    public void eliminar(Long id) {
        if (!motoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Moto no encontrada: " + id);
        }
        motoRepository.deleteById(id);
    }
}
