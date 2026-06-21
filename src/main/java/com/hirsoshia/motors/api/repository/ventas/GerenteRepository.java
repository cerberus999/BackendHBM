package com.hirsoshia.motors.api.repository.ventas;

import com.hirsoshia.motors.api.model.ventas.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    java.util.Optional<Gerente> findByEmail(String email);
}
