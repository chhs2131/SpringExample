package org.example.tddexample.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductServiceTest {
    private ProductService productService;
    private ProductPort productPort;
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        productRepository = new ProductRepository();
        productPort = new ProductAdapter(productRepository);
        productService = new ProductService(productPort);
    }

    @Test
    void 상품등록() {
        final AddProductRequest request = 상품등록요청_생성();

        productService.addProduct(request);
    }

    private static AddProductRequest 상품등록요청_생성() {
        final String name = "상품명";
        final int price = 1000;
        final Discountpolicy policy = Discountpolicy.NONE;
        final AddProductRequest request = new AddProductRequest(name, price, policy);
        return request;
    }
}
