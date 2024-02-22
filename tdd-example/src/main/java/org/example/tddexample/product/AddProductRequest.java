package org.example.tddexample.product;

import org.springframework.util.Assert;

record AddProductRequest(String name, int price, DiscountPolicy discountpolicy) {
    AddProductRequest {
        Assert.hasText(name, "상품명은 필수입니다.");
        Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다.");
        Assert.notNull(discountpolicy, "할인정책은 필수입니다.");
    }
}