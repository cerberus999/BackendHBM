package com.hirsoshia.motors.api.controller;

import com.hirsoshia.motors.api.dto.request.RegisterRequest;
import com.hirsoshia.motors.api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/usuarios")
public class AdminUsuarioController {

    private final AuthService authService;

    public AdminUsuarioController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody RegisterRequest request) {
        boolean created = authService.register(request);
        return created
                ? ResponseEntity.status(HttpStatus.CREATED).build()
                : ResponseEntity.ok().build();
    }
}
