package com.hirsoshia.motors.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VentaResponse(
        Long idVenta,
        Long idCliente,
        String clienteNombre,
        Long idGerente,
        String gerenteNombre,
        Long idMoto,
        String motoMarcaModelo,
        LocalDateTime fechaVenta,
        BigDecimal precioFinal,
        BigDecimal descuentoAplicado,
        String metodoPago,
        String notas
) {}
