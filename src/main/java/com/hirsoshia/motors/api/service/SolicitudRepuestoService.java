package com.hirsoshia.motors.api.service;

import com.hirsoshia.motors.api.dto.response.SolicitudRepuestoResponse;
import com.hirsoshia.motors.api.mapper.SolicitudRepuestoMapper;
import com.hirsoshia.motors.api.repository.clientela.SolicitudRepuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudRepuestoService {

    private final SolicitudRepuestoRepository repository;
    private final SolicitudRepuestoMapper mapper;

    public SolicitudRepuestoService(SolicitudRepuestoRepository repository,
                                     SolicitudRepuestoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<SolicitudRepuestoResponse> listarPorUnidad(Long idUnidad) {
        return repository.findByUnidadIdUnidad(idUnidad)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
