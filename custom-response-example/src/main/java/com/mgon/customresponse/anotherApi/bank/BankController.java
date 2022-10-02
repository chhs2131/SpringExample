package com.mgon.customresponse.anotherApi.bank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bank")
public class BankController {
    @GetMapping("")
    public BankDto bank() {
        return BankDto.builder()
                .name("mgon aka basak")
                .money(999999999)
                .build();
    }
}
