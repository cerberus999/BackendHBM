package com.hirsoshia.motors.api.repository.clientela;

import com.hirsoshia.motors.api.model.clientela.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<Usuario> findByIdCliente(Long idCliente);
}
