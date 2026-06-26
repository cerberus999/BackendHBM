package com.hirsoshia.motors.api.controller;

import com.hirsoshia.motors.api.dto.response.GarantiaPosventaResponse;
import com.hirsoshia.motors.api.dto.response.ReclamoTecnicoResponse;
import com.hirsoshia.motors.api.dto.response.ServicioMantenimientoResponse;
import com.hirsoshia.motors.api.dto.response.SolicitudRepuestoResponse;
import com.hirsoshia.motors.api.dto.response.UnidadClienteResponse;
import com.hirsoshia.motors.api.service.GarantiaPosventaService;
import com.hirsoshia.motors.api.service.ReclamoTecnicoService;
import com.hirsoshia.motors.api.service.ServicioMantenimientoService;
import com.hirsoshia.motors.api.service.SolicitudRepuestoService;
import com.hirsoshia.motors.api.service.UnidadClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente/unidades")
public class ClienteUnidadController {

    private final UnidadClienteService unidadClienteService;
    private final ServicioMantenimientoService servicioMantenimientoService;
    private final GarantiaPosventaService garantiaPosventaService;
    private final ReclamoTecnicoService reclamoTecnicoService;
    private final SolicitudRepuestoService solicitudRepuestoService;

    public ClienteUnidadController(UnidadClienteService unidadClienteService,
                                   ServicioMantenimientoService servicioMantenimientoService,
                                   GarantiaPosventaService garantiaPosventaService,
                                   ReclamoTecnicoService reclamoTecnicoService,
                                   SolicitudRepuestoService solicitudRepuestoService) {
        this.unidadClienteService = unidadClienteService;
        this.servicioMantenimientoService = servicioMantenimientoService;
        this.garantiaPosventaService = garantiaPosventaService;
        this.reclamoTecnicoService = reclamoTecnicoService;
        this.solicitudRepuestoService = solicitudRepuestoService;
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<UnidadClienteResponse>> listarPorCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(unidadClienteService.listarPorCliente(idCliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadClienteResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(unidadClienteService.obtenerPorId(id));
    }

    @GetMapping("/{id}/servicios")
    public ResponseEntity<List<ServicioMantenimientoResponse>> listarServicios(@PathVariable Long id) {
        return ResponseEntity.ok(servicioMantenimientoService.listarPorUnidad(id));
    }

    @GetMapping("/{id}/garantia")
    public ResponseEntity<GarantiaPosventaResponse> obtenerGarantia(@PathVariable Long id) {
        return ResponseEntity.ok(garantiaPosventaService.obtenerPorUnidad(id));
    }

    @GetMapping("/{id}/reclamos")
    public ResponseEntity<List<ReclamoTecnicoResponse>> listarReclamos(@PathVariable Long id) {
        return ResponseEntity.ok(reclamoTecnicoService.listarPorUnidad(id));
    }

    @GetMapping("/{id}/solicitudes-repuesto")
    public ResponseEntity<List<SolicitudRepuestoResponse>> listarSolicitudesRepuesto(@PathVariable Long id) {
        return ResponseEntity.ok(solicitudRepuestoService.listarPorUnidad(id));
    }
}
