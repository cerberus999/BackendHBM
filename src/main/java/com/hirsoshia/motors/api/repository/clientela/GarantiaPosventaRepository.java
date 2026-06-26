package com.hirsoshia.motors.api.repository.clientela;

import com.hirsoshia.motors.api.model.clientela.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GarantiaPosventaRepository extends JpaRepository<GarantiaPosventa, Long> {
    Optional<GarantiaPosventa> findByUnidadIdUnidad(Long idUnidad);
}
