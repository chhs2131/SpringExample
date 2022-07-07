package com.example.googleoauthlogintutorial.oauth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoogleApiRequestDto {
    private String access_token;
}
