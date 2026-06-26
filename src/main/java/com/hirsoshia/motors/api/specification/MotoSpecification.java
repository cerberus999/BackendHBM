package com.hirsoshia.motors.api.specification;

import com.hirsoshia.motors.api.model.ventas.Moto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MotoSpecification {

    public static Specification<Moto> conFiltros(String marca, String modelo, String tipo,
                                                  BigDecimal precioMin, BigDecimal precioMax) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("estado"), "disponible"));

            if (marca != null && !marca.isBlank()) {
                String[] marcas = marca.split(",");
                if (marcas.length == 1) {
                    predicates.add(cb.equal(root.get("marca"), marcas[0].trim()));
                } else {
                    var inClause = cb.in(root.get("marca"));
                    for (String m : marcas) {
                        inClause.value(m.trim());
                    }
                    predicates.add(inClause);
                }
            }

            if (modelo != null && !modelo.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("modelo")), "%" + modelo.toLowerCase() + "%"));
            }

            if (tipo != null && !tipo.isBlank()) {
                String[] tipos = tipo.split(",");
                if (tipos.length == 1) {
                    predicates.add(cb.equal(root.get("tipo"), tipos[0].trim()));
                } else {
                    var inClause = cb.in(root.get("tipo"));
                    for (String t : tipos) {
                        inClause.value(t.trim());
                    }
                    predicates.add(inClause);
                }
            }

            if (precioMin != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("precioVenta"), precioMin));
            }

            if (precioMax != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("precioVenta"), precioMax));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
