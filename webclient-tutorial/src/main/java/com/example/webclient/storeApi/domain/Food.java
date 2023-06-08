package com.example.webclient.storeApi.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    private String name;
    private int kcal;
    private int price;

    @Override
    public String toString() {
        return "음식이름:" + name + " " + kcal + "kcal " + price + "원";
    }
}
