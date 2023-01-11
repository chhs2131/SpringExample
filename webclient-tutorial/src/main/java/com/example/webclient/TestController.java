package com.example.webclient;

import com.example.webclient.util.WebClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TestController {
    private final List<Food> foods = List.of(
            new Food("카레", 1000, 7000),
            new Food("라면", 500, 5000),
            new Food("새우튀김", 2000, 12000),
            new Food("새우볶음밥", 1000, 10000)

    );

    @RequestMapping("/foods")
    public List<Food> getFoods(String name) {
        // name 단어가 포함된 음식 리스트를 return 한다.
        return foods.stream()
                .filter(food -> food.getName().contains(name))
                .collect(Collectors.toList());
    }

    @RequestMapping("/")
    public void get() {
        WebClient webClient = WebClientUtil.getBaseUrl("http://localhost:8080");
        Food foods = webClient.get()
                .uri("/foods" + "?name=" + "라면")
                .retrieve()
                //.bodyToFlux(Food.class)
                .bodyToMono(Food.class)
                //.collectList()
                .block();
        System.out.println(foods.toString());
    }
}
