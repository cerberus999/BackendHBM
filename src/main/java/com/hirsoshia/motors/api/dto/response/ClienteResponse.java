package com.hirsoshia.motors.api.dto.response;

import java.time.LocalDate;

public record ClienteResponse(
        Long idCliente,
        String nombre,
        String apellido,
        String ci,
        String telefono,
        String email,
        String direccion,
        LocalDate fechaRegistro
) {}
