package com.example.pizzas.controllers;


import com.example.pizzas.entities.Pizza;
import com.example.pizzas.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;


@RestController
public class PizzaController {

    @Autowired
    PizzaRepository pizzaRepository;


    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }



    @GetMapping("/pizzas")
    public List<Pizza> pizzas() {
        return pizzaRepository.findAll();
    }

    @GetMapping("/pizzas/{id}")
    public Optional<Pizza> read(@PathVariable int id) {

        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if (pizza.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else
            return pizza;

    }

    @PostMapping("/pizzas")
    public Pizza add(@RequestBody Pizza pizza) {
        return pizzaRepository.save(pizza);
    }


    @DeleteMapping("/pizzas/{id}")
    public boolean deletePizza(@PathVariable int id) {

        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if (pizza.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else
        pizzaRepository.deleteById(id);
        return true;
    }

    @GetMapping(value ="/pizza/search")
    public List <Pizza>  pizzaSearch (@RequestBody Map<String, String> search) {


        List<Pizza> pizzas = null;
        for (Map.Entry<String, String> entry : search.entrySet()) {
            pizzas = new ArrayList<>();
            String key = entry.getKey();
            String value = entry.getValue();
            {
                switch (key) {
                    case "name":
                        pizzas.addAll(pizzaRepository.findAllByName(value));
                        break;
                    case "price":
                        pizzas.addAll(pizzaRepository.findAllByPrice(Integer.parseInt(value)));
                        break;

                }

            }
        }
        if(pizzas.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else
        return pizzas;

    }

    @GetMapping("/pizza/name/{name}")
    public List<Pizza> findByName(@PathVariable String name) {
        List<Pizza> pizza = (pizzaRepository.findAllByName(name));
        if (pizza.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else
            return pizza;

    }
    @GetMapping("/pizza/price/{price}")
    public List<Pizza> findByPrice(@PathVariable int price){
        List<Pizza> pizza = (pizzaRepository.findAllByPrice(price));
        if (pizza.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else
            return pizza;
    }




    @PutMapping("/pizza/{id}")
    public Pizza updatePizza(@PathVariable int id,@Valid @RequestBody Pizza updatePizza) {
        Pizza pizza = pizzaRepository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

       pizza.setName(updatePizza.getName());
       pizza.setIngridients(updatePizza.getIngridients());
       pizza.setPrice(updatePizza.getPrice());


        return pizzaRepository.save(pizza);
    }



    @PatchMapping("/pizza/{id}")
    public Pizza updatePatch(@PathVariable int id, @RequestBody Map<String, String> changes){

        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        for (Map.Entry<String, String> entry : changes.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            switch (key) {
                case "name":
                    pizza.setName(value);
                    break;
                case "ingridients":
                    pizza.setIngridients(value);
                    break;
                case "price":
                    pizza.setPrice(Integer.parseInt(value));
                    break;
            }

        }
        return pizzaRepository.save(pizza);
};
}