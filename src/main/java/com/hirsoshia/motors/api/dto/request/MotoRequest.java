package com.hirsoshia.motors.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record MotoRequest(
        @NotNull Long idDistribuidor,
        @NotBlank String marca,
        @NotBlank String modelo,
        @NotNull Integer año,
        String tipo,
        Integer cilindradaCc,
        @NotNull @Positive BigDecimal precioImportacion,
        @NotNull @Positive BigDecimal precioVenta,
        @NotNull @Positive Integer stock,
        String color,
        String imagenUrl,
        String estado
) {}
