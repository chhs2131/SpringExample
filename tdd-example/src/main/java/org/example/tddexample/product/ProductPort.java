package org.example.tddexample.product;

interface ProductPort {
    void save(final Product product);

    Product getProduct(Long productId);
}
