package org.example.tddexample.order;

import org.example.tddexample.product.Product;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    private final OrderPort orderPort;

    public OrderService(OrderPort orderPort) {
        this.orderPort = orderPort;
    }

    // 포트에게 상품을 가져오라고 요청을 보냄
    // 가져온 상품을 통해 오더를 생성하고 저장
    public void createOrder(final CreateOrderRequest request) {
        final Product product = orderPort.getProductById(request.getProductId());
        final Order order = new Order(product, request.getQuantity());

        orderPort.save(order);
    }
}
