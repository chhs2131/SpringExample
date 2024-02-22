package org.example.tddexample.order;

import org.example.tddexample.product.Product;

public interface OrderPort {
    Product getProductById(Long productId);
    void save(Order order);
}