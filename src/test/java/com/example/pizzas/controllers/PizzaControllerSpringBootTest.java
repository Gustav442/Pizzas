package com.example.pizzas.controllers;

import com.example.pizzas.repositories.PizzaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PizzaControllerSpringBootTest {


    @Autowired
    private PizzaController pizzaController;

    @Test
    void getAllPizzas(){
        assertThat(pizzaController.pizzas()).hasSize(1);
    }
}