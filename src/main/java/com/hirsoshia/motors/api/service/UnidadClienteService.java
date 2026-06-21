package com.hirsoshia.motors.api.service;

import com.hirsoshia.motors.api.dto.response.UnidadClienteResponse;
import com.hirsoshia.motors.api.exception.ResourceNotFoundException;
import com.hirsoshia.motors.api.model.clientela.UnidadCliente;
import com.hirsoshia.motors.api.repository.clientela.UnidadClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadClienteService {

    private final UnidadClienteRepository unidadClienteRepository;

    public UnidadClienteService(UnidadClienteRepository unidadClienteRepository) {
        this.unidadClienteRepository = unidadClienteRepository;
    }

    public List<UnidadClienteResponse> listarPorCliente(Long idClienteRef) {
        return unidadClienteRepository.findByIdClienteRef(idClienteRef)
                .stream().map(this::toResponse).toList();
    }

    public UnidadClienteResponse obtenerPorId(Long id) {
        UnidadCliente uc = unidadClienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unidad no encontrada: " + id));
        return toResponse(uc);
    }

    private UnidadClienteResponse toResponse(UnidadCliente uc) {
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
