package com.example.webclient.controller;

import com.example.webclient.domain.Food;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final List<Food> foods = List.of(
            new Food("카레", 1000, 7000),
            new Food("라면", 500, 5000),
            new Food("새우튀김", 2000, 12000),
            new Food("새우볶음밥", 1000, 10000)

    );

    @RequestMapping("/menu")
    public List<Food> getFoods(String name) {
        // name 단어가 포함된 음식 리스트를 return 한다.
        return foods.stream()
                .filter(food -> food.getName().contains(name))
                .collect(Collectors.toList());
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "어서오십쇼!";
    }

    @RequestMapping("/")
    public void get() {
//        WebClient client = WebClient.create();
//        String result = client.get()
//                .uri("http://localhost:8080/helloworld")
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//        System.out.println(result);


        WebClient.create().get().uri("http://localhost:8080/helloworld")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println);

//        Food foods = webClient.get()
//                .uri("/foods" + "?name=" + "라면")
//                .retrieve()
//                //.bodyToFlux(Food.class)
//                .bodyToMono(Food.class)
//                //.collectList()
//                .block();
//        System.out.println(foods.toString());
    }
}
