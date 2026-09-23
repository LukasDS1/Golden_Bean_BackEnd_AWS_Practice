package com.github.coffee_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.coffee_service.model.Coffee;
import java.util.List;


@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

    List<Coffee> findByNombreNormalizado(String nombreNormalizado);

    List<Coffee> findByNombreNormalizadoContainingIgnoreCase(String nombreNormalizado);
}
