package com.hirsoshia.motors.api.service;

import com.hirsoshia.motors.api.dto.response.GarantiaPosventaResponse;
import com.hirsoshia.motors.api.exception.ResourceNotFoundException;
import com.hirsoshia.motors.api.mapper.GarantiaPosventaMapper;
import com.hirsoshia.motors.api.repository.clientela.GarantiaPosventaRepository;
import org.springframework.stereotype.Service;

@Service
public class GarantiaPosventaService {

    private final GarantiaPosventaRepository repository;
    private final GarantiaPosventaMapper mapper;

    public GarantiaPosventaService(GarantiaPosventaRepository repository,
                                   GarantiaPosventaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public GarantiaPosventaResponse obtenerPorUnidad(Long idUnidad) {
        return repository.findByUnidadIdUnidad(idUnidad)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Garantía no encontrada para la unidad: " + idUnidad));
    }
}
