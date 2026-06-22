package com.hirsoshia.motors.api.service;

import com.hirsoshia.motors.api.dto.response.UnidadClienteResponse;
import com.hirsoshia.motors.api.exception.ResourceNotFoundException;
import com.hirsoshia.motors.api.mapper.UnidadClienteMapper;
import com.hirsoshia.motors.api.model.clientela.UnidadCliente;
import com.hirsoshia.motors.api.repository.clientela.UnidadClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadClienteService {

    private final UnidadClienteRepository unidadClienteRepository;
    private final UnidadClienteMapper unidadClienteMapper;

    public UnidadClienteService(UnidadClienteRepository unidadClienteRepository,
                                UnidadClienteMapper unidadClienteMapper) {
        this.unidadClienteRepository = unidadClienteRepository;
        this.unidadClienteMapper = unidadClienteMapper;
    }

    public List<UnidadClienteResponse> listarPorCliente(Long idClienteRef) {
        return unidadClienteRepository.findByIdClienteRef(idClienteRef)
                .stream().map(unidadClienteMapper::toDto).toList();
    }

    public UnidadClienteResponse obtenerPorId(Long id) {
        UnidadCliente uc = unidadClienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unidad no encontrada: " + id));
        return unidadClienteMapper.toDto(uc);
    }
}
