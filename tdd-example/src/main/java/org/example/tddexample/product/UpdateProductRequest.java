package org.example.tddexample.product;

record UpdateProductRequest(
    String name,
    int price,
    DiscountPolicy discountPolicy
) {
}
