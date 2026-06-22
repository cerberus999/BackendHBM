package com.hirsoshia.motors.api.service;

import com.hirsoshia.motors.api.dto.request.MotoRequest;
import com.hirsoshia.motors.api.dto.response.MotoResponse;
import com.hirsoshia.motors.api.exception.ResourceNotFoundException;
import com.hirsoshia.motors.api.mapper.MotoMapper;
import com.hirsoshia.motors.api.model.ventas.Distribuidor;
import com.hirsoshia.motors.api.model.ventas.Moto;
import com.hirsoshia.motors.api.repository.ventas.DistribuidorRepository;
import com.hirsoshia.motors.api.repository.ventas.MotoRepository;
import org.springframework.stereotype.Service;

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

        return motoMapper.toDto(motoRepository.save(moto));
    }

    public void eliminar(Long id) {
        if (!motoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Moto no encontrada: " + id);
        }
        motoRepository.deleteById(id);
    }
}
