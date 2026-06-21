package com.hirsoshia.motors.api.repository.ventas;

import com.hirsoshia.motors.api.model.ventas.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByClienteIdCliente(Long clienteId);
}
