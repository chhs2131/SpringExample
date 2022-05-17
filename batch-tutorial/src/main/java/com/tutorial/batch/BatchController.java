package com.tutorial.batch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BatchController {
    @GetMapping(value = "/test")
    public String test() {
        return "return 12345 가나다";
    }
}
