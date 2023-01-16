package com.example.webclient.kakaoApi.properties;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KakaoProperties {
    private final String restApiKey = "5105c538e874bd1869e7115df0429707";
    private final String baseUrl = "https://dapi.kakao.com";
    private final String defaultHeader = "KakaoAK " + restApiKey;
}
