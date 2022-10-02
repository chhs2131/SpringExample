package com.mgon.customresponse.anotherApi.bank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankDto {
    private String name;
    private int money;
}
