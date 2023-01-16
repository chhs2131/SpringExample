package com.example.webclient.storeApi.controller;

import com.example.webclient.storeApi.domain.Food;
import com.example.webclient.storeApi.util.WebClientUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/delivery")
public class FoodDeliveryAppController {
@GetMapping("/welcome")
public String deliveryWelcome(@RequestParam boolean block, @RequestParam int people) {
    long startTime = System.currentTimeMillis();  // 시작시간 측정

    welcomeCustomer(block, people);  // 손님을 맞이합니다.

    long endTime = System.currentTimeMillis();  // 완료시간 측정
    return (double) (endTime - startTime) / 1000 + " 초";
}

private static void welcomeCustomer(boolean block, int people) {
    int n = 0;
    while (people-- > 0) {
        Mono<String> mono = WebClient.create()
                .get()
                .uri("http://localhost:8080/store/welcome")
                .retrieve()
                .bodyToMono(String.class);

        if (block) {
            System.out.println(mono.block());
        } else {
            mono.subscribe(System.out::println);
        }
    }
}

@GetMapping("/menu")
public List<Food> getMenu(@RequestParam String name) {
    // flux 만들기 (연결 정보를 담은)
    Flux<Food> flux = WebClientUtil.getBaseUrl("http://localhost:8080/store")
            .get()
            .uri(builder -> builder.path("/menu")
                    .queryParam("name", name)
                    .build())
            .retrieve()
            .bodyToFlux(Food.class);

    // 실제로 menu 받아오기
    List<Food> menu = flux.toStream().collect(Collectors.toList());
    return menu;
}
}
