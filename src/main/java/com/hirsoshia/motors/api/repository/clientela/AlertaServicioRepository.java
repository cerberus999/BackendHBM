package com.hirsoshia.motors.api.repository.clientela;

import com.hirsoshia.motors.api.model.clientela.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertaServicioRepository extends JpaRepository<AlertaServicio, Long> {
    List<AlertaServicio> findByUnidadIdUnidad(Long idUnidad);
}
