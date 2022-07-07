package com.example.googleoauthlogintutorial.oauth.controller;

import com.example.googleoauthlogintutorial.common.util.GoogleOAuthConfigUtils;
import com.example.googleoauthlogintutorial.oauth.dto.GoogleApiRequestDto;
import com.example.googleoauthlogintutorial.oauth.dto.GoogleLoginDto;
import com.example.googleoauthlogintutorial.oauth.dto.GoogleLoginRequestDto;
import com.example.googleoauthlogintutorial.oauth.dto.GoogleLoginResponseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class OAuthController {
    private final GoogleOAuthConfigUtils configUtils;

    public OAuthController(GoogleOAuthConfigUtils googleOAuthConfigUtils) {
        this.configUtils = googleOAuthConfigUtils;
    }

    @GetMapping("/test")
    public String test() throws Exception {
        return "test12345";
    }

    @GetMapping("/googleConfig")
    public String googleConfigUtils() throws Exception {
        log.info(String.valueOf(configUtils));
        return "Configuration file";
    }

    @GetMapping(value = "/oauth/google/login")
    public ResponseEntity<Object> moveGoogleInitUrl() {
        String authUrl = configUtils.googleInitUrl();
        log.info("인증주소: " + authUrl);
        URI redirectUri = null;
        try {
            redirectUri = new URI(authUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(redirectUri);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "/oauth/google/redirect")
    public ResponseEntity<GoogleLoginDto> redirectGoogleLogin(
            @RequestParam(value = "code") String authCode
    ) {
        // HTTP 통신을 위해 RestTemplate 활용
        RestTemplate restTemplate = new RestTemplate();
        GoogleLoginRequestDto requestParams = GoogleLoginRequestDto.builder()
                .clientId(configUtils.getClientId())
                .clientSecret(configUtils.getSecretKey())
                .code(authCode)
                .redirectUri(configUtils.getRedirectUrl())
                .grantType("authorization_code")
                .build();

        try {
            // Http Header 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<GoogleLoginRequestDto> httpRequestEntity = new HttpEntity<>(requestParams, headers);
            log.info("httpRequestEntity: " + httpRequestEntity.toString());
            ResponseEntity<String> apiResponseJson = restTemplate.postForEntity(configUtils.getAuthUrl() + "/token", httpRequestEntity, String.class);
            log.info("apiResponseJson: " + apiResponseJson);  // 이곳에 AccessToken 정보가 포함되어 있음.

            // ObjectMapper를 통해 String to Object로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // NULL이 아닌 값만 응답받기(NULL인 경우는 생략)
            GoogleLoginResponseDto googleLoginResponse = objectMapper.readValue(apiResponseJson.getBody(), new TypeReference<GoogleLoginResponseDto>() {});

            // 사용자의 정보는 JWT Token으로 저장되어 있고, Id_Token에 값을 저장한다.
            String jwtToken = googleLoginResponse.getIdToken();

            // JWT Token을 전달해 JWT 저장된 사용자 정보 확인
            String requestUrl = UriComponentsBuilder.fromHttpUrl(configUtils.getAuthUrl() + "/tokeninfo").queryParam("id_token", jwtToken).toUriString();

            String resultJson = restTemplate.getForObject(requestUrl, String.class);

            if(resultJson != null) {
                GoogleLoginDto userInfoDto = objectMapper.readValue(resultJson, new TypeReference<GoogleLoginDto>() {});

                return ResponseEntity.ok().body(userInfoDto);
            }
            else {
                throw new Exception("Google OAuth failed!");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/oauth/google/userinfo")
    public ResponseEntity<String> userinfoEmail(@RequestParam(value = "code") String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            log.info("userinfo url: " + configUtils.getUserinfoUrl());
            ResponseEntity<String> apiResponseJson = restTemplate.getForEntity(configUtils.getUserinfoUrl() + "?access_token=" + accessToken, String.class);
            log.info("apiResponseJson: " + apiResponseJson);

            return apiResponseJson;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().body(null);
    }
}
