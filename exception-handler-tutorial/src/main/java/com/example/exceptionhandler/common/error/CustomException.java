package com.example.exceptionhandler.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private final GlobalErrorCode errorCode;
}
