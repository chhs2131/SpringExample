package com.example.exceptionhandler.common.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final int status;
    private final String message;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public static ResponseEntity<ErrorResponse> toResponseEntity(GlobalErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getStatus().value())
                        .message(errorCode.getMessage())
                        .build());
    }
}
