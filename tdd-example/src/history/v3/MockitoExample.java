package org.example.tddexample.product;


import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    private ProductService productService;
//    private StubProductPort productPort = new StubProductPort();
    private ProductPort productPort;

    @BeforeEach
    void setup() {

        productPort = Mockito.mock(ProductPort.class);
        productService = new ProductService(productPort);
    }

    @Test
    void 상품수정() {
        final Long productId = 1L;
        final UpdateProductRequest request = new UpdateProductRequest("상품 수정", 2000, DiscountPolicy.NONE);
        final Product product = new Product("상품명", 1000, DiscountPolicy.NONE);

        Mockito.when(productPort.getProduct(productId)).thenReturn(product);
        // productPort.product_will_return = product;

        productService.updateProduct(productId, request);

        assertThat(product.getName()).isEqualTo("상품 수정");
        assertThat(product.getPrice()).isEqualTo(2000);
    }

//    private static class StubProductPort implements ProductPort {
//        public Product product_will_return;
//
//        @Override
//        public void save(final Product product) {
//        }
//
//        @Override
//        public Product getProduct(final Long productId) {
//            return product_will_return;
//        }
//    }
}
