package com.hirsoshia.motors.api.repository.ventas;

import com.hirsoshia.motors.api.model.ventas.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MotoRepository extends JpaRepository<Moto, Long> {
    List<Moto> findByEstado(String estado);
    List<Moto> findByMarca(String marca);
    List<Moto> findByMarcaAndEstado(String marca, String estado);
}
