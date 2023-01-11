package com.example.webclient;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Food {
    private final String name;
    private final int kcal;
    private final int price;

    @Override
    public String toString() {
        return "음식이름:" + name + " " + kcal + "kcal " + price + "원";
    }
}
