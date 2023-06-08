package com.example.webclient.storeApi.controller;

import com.example.webclient.storeApi.domain.Food;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String welcome() throws InterruptedException {
        Thread.sleep(100);  // 입장 후 0.1초후에 인사한다.
        return "어서오십쇼!";
    }
}
