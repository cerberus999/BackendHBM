package com.hirsoshia.motors.api.mapper;

import com.hirsoshia.motors.api.dto.response.VentaResponse;
import com.hirsoshia.motors.api.model.ventas.Venta;
import org.springframework.stereotype.Component;

@Component
public class VentaMapper implements DtoMapper<Venta, VentaResponse> {

    @Override
    public VentaResponse toDto(Venta venta) {
        return new VentaResponse(
                venta.getIdVenta(),
                venta.getCliente().getIdCliente(),
                venta.getCliente().getNombre() + " " + venta.getCliente().getApellido(),
                venta.getGerente().getIdGerente(),
                venta.getGerente().getNombre() + " " + venta.getGerente().getApellido(),
                venta.getMoto().getIdMoto(),
                venta.getMoto().getMarca() + " " + venta.getMoto().getModelo(),
                venta.getFechaVenta(),
                venta.getPrecioFinal(),
                venta.getDescuentoAplicado(),
                venta.getMetodoPago(),
                venta.getNotas());
    }
}
