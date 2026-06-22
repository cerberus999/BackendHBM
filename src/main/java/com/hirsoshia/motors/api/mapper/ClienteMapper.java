package com.hirsoshia.motors.api.mapper;

import com.hirsoshia.motors.api.dto.response.ClienteResponse;
import com.hirsoshia.motors.api.model.ventas.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper implements DtoMapper<Cliente, ClienteResponse> {

    @Override
    public ClienteResponse toDto(Cliente cliente) {
        return new ClienteResponse(
                cliente.getIdCliente(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCi(),
                cliente.getTelefono(),
                cliente.getEmail(),
                cliente.getDireccion(),
                cliente.getFechaRegistro());
    }
}
