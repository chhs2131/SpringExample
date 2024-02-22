package org.example.tddexample.product;


import static org.assertj.core.api.Assertions.*;
import static org.example.tddexample.product.ProductSteps.상품수정요청;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

//    @Test
//    void 상품수정() {
//        productService.addProduct(ProductSteps.상품등록요청_생성());
//        final Long productId = 1L;
//        final UpdateProductRequest request = 상품수정요청();
//
//        final GetProductResponse rr = productService.getProduct(productId).getBody();
//
//        productService.updateProduct(productId, request);
//
//        final ResponseEntity<GetProductResponse> response = productService.getProduct(productId);
//        final GetProductResponse productResponse = response.getBody();
//
//        assertThat(productResponse.name()).isEqualTo("상품 수정");
//        assertThat(productResponse.price()).isEqualTo(2000);
//    }

}
