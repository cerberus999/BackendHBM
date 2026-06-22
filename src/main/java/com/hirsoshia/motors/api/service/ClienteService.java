package com.hirsoshia.motors.api.service;

import com.hirsoshia.motors.api.dto.request.ClienteRequest;
import com.hirsoshia.motors.api.dto.response.ClienteResponse;
import com.hirsoshia.motors.api.exception.ResourceNotFoundException;
import com.hirsoshia.motors.api.mapper.ClienteMapper;
import com.hirsoshia.motors.api.model.ventas.Cliente;
import com.hirsoshia.motors.api.repository.ventas.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteResponse> listarTodos() {
        return clienteRepository.findAll().stream().map(clienteMapper::toDto).toList();
    }

    public ClienteResponse obtenerPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado: " + id));
        return clienteMapper.toDto(cliente);
    }

    public ClienteResponse crear(ClienteRequest request) {
        Cliente cliente = Cliente.builder()
                .nombre(request.nombre())
                .apellido(request.apellido())
                .ci(request.ci())
                .telefono(request.telefono())
                .email(request.email())
                .direccion(request.direccion())
                .build();
        return clienteMapper.toDto(clienteRepository.save(cliente));
    }

    public ClienteResponse actualizar(Long id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado: " + id));
        cliente.setNombre(request.nombre());
        cliente.setApellido(request.apellido());
        cliente.setCi(request.ci());
        cliente.setTelefono(request.telefono());
        cliente.setEmail(request.email());
        cliente.setDireccion(request.direccion());
        return clienteMapper.toDto(clienteRepository.save(cliente));
    }

    public void eliminar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
