package com.hirsoshia.motors.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record VentaRequest(
        @NotNull Long idCliente,
        @NotNull Long idGerente,
        @NotNull Long idMoto,
        @NotNull @Positive BigDecimal precioFinal,
        BigDecimal descuentoAplicado,
        String metodoPago,
        String notas
) {}
