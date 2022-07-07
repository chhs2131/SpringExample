package com.example.googleoauthlogintutorial.common.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Configuration
@ConfigurationProperties("google")
public class GoogleOAuthConfigUtils {
    private String clientId;
    private String secretKey;
    private String authUrl;
    private String loginUrl;
    private String redirectUrl;
    private String userinfoUrl;

    // Google 로그인 URL 생성 로직
    public String googleInitUrl() {
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", getClientId());
        params.put("redirect_uri", getRedirectUrl());
        params.put("response_type", "code");
        params.put("scope", "email");

        String paramStr = params.entrySet().stream()
                .map(param -> param.getKey() + "=" + param.getValue())
                .collect(Collectors.joining("&"));

        return getLoginUrl()
                + "/o/oauth2/v2/auth"
                + "?"
                + paramStr;
    }
}
