package com.example.exceptionhandler.api.test;

import com.example.exceptionhandler.common.error.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.exceptionhandler.common.error.GlobalErrorCode.DUPLICATE_RESOURCE;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @GetMapping(value = "/")
    public String testReturn() {
        return "test12345";
    }

    @GetMapping(value = "/exception/illegal")
    public String testException() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    @GetMapping(value = "/exception/custom")
    public String testException2() {
        throw new CustomException(DUPLICATE_RESOURCE);
    }
}
