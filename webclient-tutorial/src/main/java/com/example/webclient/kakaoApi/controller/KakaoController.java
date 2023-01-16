package com.example.webclient.kakaoApi.controller;

import com.example.webclient.kakaoApi.properties.KakaoBlogProperties;
import com.example.webclient.kakaoApi.properties.KakaoWebProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoController {
    private final WebClient kakaoWebClient;  // bean으로 등록한 KakaoWebClient를 사용
    private final KakaoWebProperties kakaoWebProperties;
    private final KakaoBlogProperties kakaoBlogProperties;

    @GetMapping("/web")
    public String kakaoWebSearch() {
        Mono<String> mono = kakaoWebClient.method(kakaoWebProperties.getMethod())
                .uri(builder -> builder.path(kakaoWebProperties.getPath())
                        .queryParam("query", "aaa")
                        .build()
                )
                .retrieve()
                .bodyToMono(String.class);

        // mono.subscribe(System.out::println);
        return mono.block();
    }

    @GetMapping("/blog")
    public String kakaoBlogSearch() {
        Mono<String> mono = kakaoWebClient.method(kakaoBlogProperties.getMethod())
                .uri(builder -> builder.path(kakaoBlogProperties.getPath())
                        .queryParam("query", "aaa")
                        .build()
                )
                .retrieve()
                .bodyToMono(String.class);

        // mono.subscribe(System.out::println);
        return mono.block();
    }
}
