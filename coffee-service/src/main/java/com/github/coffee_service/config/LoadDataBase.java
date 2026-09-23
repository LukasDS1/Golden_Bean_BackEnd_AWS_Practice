package com.github.coffee_service.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.coffee_service.model.Coffee;
import com.github.coffee_service.repository.CoffeeRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class LoadDataBase {

    @Bean
    CommandLineRunner initDatabase(CoffeeRepository coffeeRepository){
        return args -> {
            if (coffeeRepository.count() == 0) {
                
                Coffee c1 = new Coffee(
                    null, 
                    "Espresso Italiano", 
                    "espresso italiano", 
                    "/img/espresso.jpg", 
                    "Café corto y concentrado, clásico de Italia con fuerte aroma y sabor.", 
                    1800);

                Coffee c2 = new Coffee(
                    null,
                    "Café Americano",
                    "cafe americano",
                    "/img/americano.jpeg",
                    "Espresso rebajado con agua caliente, suave y balanceado.",
                    2200
                );

                Coffee c3 = new Coffee(
                    null,
                    "Cappuccino",
                    "cappuccino",
                    "/img/capuccino.jpg",
                    "Café espresso con leche caliente y espuma espesa.",
                    2800
                );

                Coffee c4 = new Coffee(
                    null,
                    "Latte",
                    "latte",
                    "/img/late.jpg",
                    "Espresso suave con abundante leche vaporizada y una fina capa de espuma.",
                    3000
                );

                Coffee c5 = new Coffee(
                    null,
                    "Mocha",
                    "mocha",
                    "/img/mocha.jpg",
                    "Espresso con chocolate, leche vaporizada y crema batida.",
                    3500
                );

                Coffee c6 = new Coffee(
                    null,
                    "Flat White",
                    "flat white",
                    "/img/flatwhite.jpg",
                    "Bebida cremosa con espresso y microespuma de leche.",
                    3300
                );

                Coffee c7 = new Coffee(
                    null,
                    "Macchiato",
                    "macchiato",
                    "/img/macchiato.jpg",
                    "Espresso manchado con una pequeña cantidad de leche espumada.",
                    2600
                );

                Coffee c8 = new Coffee(
                    null,
                    "Café Helado",
                    "cafe helado",
                    "/img/cafe_helado.jpg",
                    "Café frío servido con hielo, refrescante para días calurosos.",
                    2500
                );

                Coffee c9 = new Coffee(
                    null,
                    "Affogato",
                    "affogato",
                    "/img/affogato.jpg",
                    "Postre de helado de vainilla bañado con espresso caliente.",
                    3800
                );

                Coffee c10 = new Coffee(
                    null,
                    "Café Dalgona",
                    "cafe dalgona",
                    "/img/dalgona.jpg",
                    "Café batido cremoso servido sobre leche fría o caliente.",
                    3200
                );

                coffeeRepository.saveAll(List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));
            } else {
                System.out.println("Datos ya existen. No se cargaron.");
            } 
        };
    }
}

    // id;
    // nombre;
    // nombre_normalizado;
    // imagen;
    // descripcion;
    // precio;