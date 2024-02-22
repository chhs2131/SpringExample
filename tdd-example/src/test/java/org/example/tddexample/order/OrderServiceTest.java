package org.example.tddexample.order;

import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.example.tddexample.order.Order;
import org.example.tddexample.product.DiscountPolicy;
import org.example.tddexample.product.Product;
import org.example.tddexample.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class OrderServiceTest {
    private OrderService orderService;
    private OrderPort orderPort;

    @BeforeEach
    void setup() {
        final OrderRepository orderRepository = new OrderRepository();
//        orderPort = new OrderAdapter(null, orderRepository);
        orderPort = new OrderPort() {
            @Override
            public Product getProductById(final Long productId) {
                return new Product("상품명", 1000, DiscountPolicy.NONE);
            }

            @Override
            public void save(final Order order) {
                orderRepository.save(order);
            }
        };
        orderService = new OrderService(orderPort);
    }

    @Test
    void 상품주문() {
        final CreateOrderRequest request = 상품주문요청_생성();

        orderService.createOrder(request);
    }

    public static CreateOrderRequest 상품주문요청_생성() {
        final Long productId = 1L;
        final int quantity = 2;
        final CreateOrderRequest request = new CreateOrderRequest(productId, quantity);
        return request;
    }
}
