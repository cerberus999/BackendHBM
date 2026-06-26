package com.hirsoshia.motors.api.repository.clientela;

import com.hirsoshia.motors.api.model.clientela.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UnidadClienteRepository extends JpaRepository<UnidadCliente, Long> {
    List<UnidadCliente> findByIdClienteRef(Long idClienteRef);
    java.util.Optional<UnidadCliente> findByIdVentaRef(Long idVentaRef);
}
