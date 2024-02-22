package org.example.tddexample.product;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void update() {
        final Product product = new Product("상품명", 1000, DiscountPolicy.NONE);

        product.update("상품 수정", 2000, DiscountPolicy.NONE);

        Assertions.assertThat(product.getName()).isEqualTo("상품 수정");
        Assertions.assertThat(product.getPrice()).isEqualTo(2000);
    }
}