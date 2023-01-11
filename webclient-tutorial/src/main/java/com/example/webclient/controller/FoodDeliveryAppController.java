package com.example.webclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/delivery")
public class FoodDeliveryAppController {
    @GetMapping("/welcome")
    public String deliveryWelcome() {
        Mono<String> mono = WebClient.create()
                .get()
                .uri("http://localhost:8080/helloworld")
                .retrieve()
                .bodyToMono(String.class);
                //.subscribe(System.out::println);

        return mono.block();
    }
}
