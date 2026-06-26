package com.hirsoshia.motors.api.service;

import com.hirsoshia.motors.api.dto.response.ReclamoTecnicoResponse;
import com.hirsoshia.motors.api.mapper.ReclamoTecnicoMapper;
import com.hirsoshia.motors.api.repository.clientela.ReclamoTecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReclamoTecnicoService {

    private final ReclamoTecnicoRepository repository;
    private final ReclamoTecnicoMapper mapper;

    public ReclamoTecnicoService(ReclamoTecnicoRepository repository,
                                 ReclamoTecnicoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ReclamoTecnicoResponse> listarPorUnidad(Long idUnidad) {
        return repository.findByUnidadIdUnidad(idUnidad)
                .stream().map(mapper::toDto).toList();
    }
}
