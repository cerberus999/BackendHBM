package com.hirsoshia.motors.api.service;

import com.hirsoshia.motors.api.dto.response.ServicioMantenimientoResponse;
import com.hirsoshia.motors.api.mapper.ServicioMantenimientoMapper;
import com.hirsoshia.motors.api.repository.clientela.ServicioMantenimientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioMantenimientoService {

    private final ServicioMantenimientoRepository repository;
    private final ServicioMantenimientoMapper mapper;

    public ServicioMantenimientoService(ServicioMantenimientoRepository repository,
                                        ServicioMantenimientoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ServicioMantenimientoResponse> listarPorUnidad(Long idUnidad) {
        return repository.findByUnidadIdUnidad(idUnidad)
                .stream().map(mapper::toDto).toList();
    }
}
