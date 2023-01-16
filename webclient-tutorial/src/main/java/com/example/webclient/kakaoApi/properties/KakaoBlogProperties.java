package com.example.webclient.kakaoApi.properties;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
@Getter
public class KakaoBlogProperties {
    private final HttpMethod method = HttpMethod.GET;
    private final String path = "/v2/search/blog";
}
