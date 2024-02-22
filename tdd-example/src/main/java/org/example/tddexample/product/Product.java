package org.example.tddexample.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.Assert;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private DiscountPolicy discountpolicy;

    public Product(String name, int price, DiscountPolicy discountpolicy) {
        Assert.hasText(name, "상품명은 필수입니다.");
        Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다.");
        Assert.notNull(discountpolicy, "할인 정책은 필수입니다.");

        this.name = name;
        this.price = price;
        this.discountpolicy = discountpolicy;
    }

    // 비즈니스 로직은 반드시 테스트를 해야함
    public void update(final String name, final int price, final DiscountPolicy discountpolicy) {
        Assert.hasText(name, "상품명은 필수입니다.");
        Assert.isTrue(price > 0, "상품 가격은 0보다 커야합니다.");
        Assert.notNull(discountpolicy, "할인 정책은 필수입니다.");

        this.name = name;
        this.price = price;
        this.discountpolicy = discountpolicy;
    }
}
