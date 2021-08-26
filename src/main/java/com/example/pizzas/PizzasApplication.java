package com.example.pizzas;

import com.example.pizzas.entities.*;
import com.example.pizzas.repositories.PizzaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class PizzasApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PizzasApplication.class);
app.setDefaultProperties(Collections.singletonMap("server.port", "8082"));
app.run(args);
    }


    @Bean
    public CommandLineRunner init(PizzaRepository pizzaRepository){

        return args -> {
            if (pizzaRepository.count() == 0 ) {
                pizzaRepository.save(new Pizza( "Hawaii", "Ost, Tomatsås, Skinka, Ananas", 80));}
};
    }

}
