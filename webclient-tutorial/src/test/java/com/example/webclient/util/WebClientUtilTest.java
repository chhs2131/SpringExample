package com.example.webclient.util;

import com.example.webclient.Food;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebClientUtilTest {

    @Test
    void getBaseUrl() {
        WebClient webClient = WebClientUtil.getBaseUrl("http://localhost:8080");
        List<Food> foods = webClient.get()
                .uri("/foods" + "?name=" + "라면")
                .retrieve()
                .bodyToFlux(Food.class)
                .collectList()
                .block();
        System.out.println(foods.toString());
    }
}
