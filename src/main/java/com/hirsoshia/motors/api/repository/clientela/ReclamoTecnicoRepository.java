package com.hirsoshia.motors.api.repository.clientela;

import com.hirsoshia.motors.api.model.clientela.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReclamoTecnicoRepository extends JpaRepository<ReclamoTecnico, Long> {
    List<ReclamoTecnico> findByUnidadIdUnidad(Long idUnidad);
}
