package org.example.tddexample.product;

import org.springframework.util.Assert;

class Product {
    private Long id;
    private final String name;
    private final int price;
    private final Discountpolicy discountpolicy;

    Product(String name, int price, Discountpolicy discountpolicy) {
        Assert.hasText(name, "상품명은 필수입니다.");
        Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다.");
        Assert.notNull(discountpolicy, "할인 정책은 필수입니다.");

        this.name = name;
        this.price = price;
        this.discountpolicy = discountpolicy;
    }

    public void assignId(final Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
