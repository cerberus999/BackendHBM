package com.hirsoshia.motors.api.controller;

import com.hirsoshia.motors.api.dto.request.MotoRequest;
import com.hirsoshia.motors.api.dto.response.MotoResponse;
import com.hirsoshia.motors.api.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/motos")
public class AdminMotoController {

    private final MotoService motoService;

    public AdminMotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping
    public ResponseEntity<List<MotoResponse>> listarTodas() {
        return ResponseEntity.ok(motoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(motoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<MotoResponse> crear(@Valid @RequestBody MotoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(motoService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoResponse> actualizar(@PathVariable Long id,
                                                    @Valid @RequestBody MotoRequest request) {
        return ResponseEntity.ok(motoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        motoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
