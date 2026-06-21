package com.hirsoshia.motors.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(
        @NotBlank String nombre,
        @NotBlank String apellido,
        @NotBlank String ci,
        String telefono,
        String email,
        String direccion
) {}
