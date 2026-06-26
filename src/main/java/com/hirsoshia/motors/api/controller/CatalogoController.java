package com.hirsoshia.motors.api.controller;

import com.hirsoshia.motors.api.dto.response.MotoResponse;
import com.hirsoshia.motors.api.service.MotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {

    private final MotoService motoService;

    public CatalogoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping
    public ResponseEntity<List<MotoResponse>> listarDisponibles() {
        return ResponseEntity.ok(motoService.listarDisponibles());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MotoResponse>> buscar(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) BigDecimal precioMin,
            @RequestParam(required = false) BigDecimal precioMax) {
        return ResponseEntity.ok(motoService.buscar(marca, modelo, tipo, precioMin, precioMax));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(motoService.obtenerPorId(id));
    }
}
