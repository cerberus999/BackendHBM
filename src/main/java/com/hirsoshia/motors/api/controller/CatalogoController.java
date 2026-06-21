package com.hirsoshia.motors.api.controller;

import com.hirsoshia.motors.api.dto.response.MotoResponse;
import com.hirsoshia.motors.api.service.MotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<MotoResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(motoService.obtenerPorId(id));
    }
}
