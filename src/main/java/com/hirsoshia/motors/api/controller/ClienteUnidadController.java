package com.hirsoshia.motors.api.controller;

import com.hirsoshia.motors.api.dto.response.UnidadClienteResponse;
import com.hirsoshia.motors.api.service.UnidadClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente/unidades")
public class ClienteUnidadController {

    private final UnidadClienteService unidadClienteService;

    public ClienteUnidadController(UnidadClienteService unidadClienteService) {
        this.unidadClienteService = unidadClienteService;
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<UnidadClienteResponse>> listarPorCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(unidadClienteService.listarPorCliente(idCliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadClienteResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(unidadClienteService.obtenerPorId(id));
    }
}
