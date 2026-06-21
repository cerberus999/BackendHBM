package com.hirsoshia.motors.api.repository.ventas;

import com.hirsoshia.motors.api.model.ventas.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    java.util.Optional<Cliente> findByEmail(String email);
    java.util.Optional<Cliente> findByCi(String ci);
}
