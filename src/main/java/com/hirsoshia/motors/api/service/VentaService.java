package com.hirsoshia.motors.api.service;

import com.hirsoshia.motors.api.dto.request.VentaRequest;
import com.hirsoshia.motors.api.dto.response.VentaResponse;
import com.hirsoshia.motors.api.exception.ResourceNotFoundException;
import com.hirsoshia.motors.api.model.ventas.*;
import com.hirsoshia.motors.api.repository.ventas.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final GerenteRepository gerenteRepository;
    private final MotoRepository motoRepository;

    public VentaService(VentaRepository ventaRepository,
                        ClienteRepository clienteRepository,
                        GerenteRepository gerenteRepository,
                        MotoRepository motoRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.gerenteRepository = gerenteRepository;
        this.motoRepository = motoRepository;
    }

    public List<VentaResponse> listarTodas() {
        return ventaRepository.findAll().stream().map(this::toResponse).toList();
    }

    public List<VentaResponse> listarPorCliente(Long clienteId) {
        return ventaRepository.findByClienteIdCliente(clienteId).stream().map(this::toResponse).toList();
    }

    public VentaResponse obtenerPorId(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada: " + id));
        return toResponse(venta);
    }

    @Transactional
    public VentaResponse crear(VentaRequest request) {
        Cliente cliente = clienteRepository.findById(request.idCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        Gerente gerente = gerenteRepository.findById(request.idGerente())
                .orElseThrow(() -> new ResourceNotFoundException("Gerente no encontrado"));
        Moto moto = motoRepository.findById(request.idMoto())
                .orElseThrow(() -> new ResourceNotFoundException("Moto no encontrada"));

        if (moto.getStock() < 1) {
            throw new IllegalStateException("Stock insuficiente para la moto: " + moto.getModelo());
        }

        moto.setStock(moto.getStock() - 1);
        motoRepository.save(moto);

        Venta venta = Venta.builder()
                .cliente(cliente)
                .gerente(gerente)
                .moto(moto)
                .precioFinal(request.precioFinal())
                .descuentoAplicado(request.descuentoAplicado() != null ? request.descuentoAplicado() : java.math.BigDecimal.ZERO)
                .metodoPago(request.metodoPago())
                .notas(request.notas())
                .build();

        return toResponse(ventaRepository.save(venta));
    }

    @Transactional
    public void anular(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada: " + id));

        Moto moto = venta.getMoto();
        moto.setStock(moto.getStock() + 1);
        motoRepository.save(moto);

        ventaRepository.delete(venta);
    }

    private VentaResponse toResponse(Venta venta) {
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
