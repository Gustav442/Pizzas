package com.example.pizzas.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pizzas.entities.Pizza;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    List<Pizza> findAllByName(String name);
    List<Pizza> findAllByPrice(Integer price);
    List<Pizza> findAllByIngridientsContaining(String ingridients);
}
