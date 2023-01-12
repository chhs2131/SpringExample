package com.example.webclient.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
// @RequiredArgsConstructor
public class WebClientUtil {
    public static WebClient getBaseUrl(final String url) {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
