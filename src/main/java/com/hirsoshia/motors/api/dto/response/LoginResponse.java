package com.hirsoshia.motors.api.dto.response;

public record LoginResponse(
        String token,
        String tipo,
        String username,
        String rol,
        Long idUsuario,
        Long idGerente,
        Long idCliente
) {}
