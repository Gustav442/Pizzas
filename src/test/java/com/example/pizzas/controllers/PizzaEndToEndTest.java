package com.example.pizzas.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PizzaEndToEndTest {

    @LocalServerPort
    private int port;



    @Test
    void getPizzaReturnsListOfPizzas(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost"+port+"/pizzas"))
                .build();
       var response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .join();

       assertThat(response.statusCode()).isEqualTo(200);
       assertThat(response.statusCode()).isEqualTo("[{\"id\":1,\"name\":\"Hawaii\",\"ingridients\":\"Ost, Tomatsås, Skinka, Ananas\",\"price\":80}]");
    }


}