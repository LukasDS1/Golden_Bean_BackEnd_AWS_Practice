package com.github.coffee_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.coffee_service.exception.CoffeeNotFoundException;
import com.github.coffee_service.model.Coffee;
import com.github.coffee_service.repository.CoffeeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public List<Coffee> getAll() {
        return coffeeRepository.findAll();
    }

    public Coffee getById(Long id) {
        return coffeeRepository.findById(id)
            .orElseThrow(() -> new  CoffeeNotFoundException("Coffee not found with id: " + id));
    }

    public List<Coffee> getByNormalized(String name) {
        String normalized = name.toLowerCase()
        .trim()
        .replaceAll("\\s+", "_")
        .replaceAll("[^a-z0-9_áéíóúñü-]", "");

        return coffeeRepository.findByNombreNormalizadoContainingIgnoreCase(normalized);
    }

    public Coffee create(Coffee coffee) {
        if (coffee == null) {
            throw new RuntimeException("Coffee cannot be null");
        }

        if (coffee.getNombre() == null || coffee.getNombre().isBlank()) {
            throw new RuntimeException("Coffee name cannot be empty");
        }

        return coffeeRepository.save(coffee);
    }

    public Coffee update(Long id, Coffee coffeeData) {
        Coffee existing = coffeeRepository.findById(id)
            .orElseThrow(() -> new CoffeeNotFoundException("Coffee not found with id: " + id)) ;

        existing.setNombre(coffeeData.getNombre());
        existing.setNombreNormalizado(coffeeData.getNombreNormalizado());
        existing.setImagen(coffeeData.getImagen());
        existing.setDescripcion(coffeeData.getDescripcion());
        existing.setPrecioCLP(coffeeData.getPrecioCLP());

        return coffeeRepository.save(existing);
    }

    public void delete(Long id) {
        if (!coffeeRepository.existsById(id)) {
            throw new CoffeeNotFoundException("Coffee not found with id: " + id);
        }

        coffeeRepository.deleteById(id);
    }
}
