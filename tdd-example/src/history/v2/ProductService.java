package org.example.tddexample.product;

import org.springframework.stereotype.Component;

@Component
class ProductService {
    private final ProductPort productPort;

    ProductService(final ProductPort productPort) {
        this.productPort = productPort;
    }

    public void addProduct(final AddProductRequest request) {
        final Product product = new Product(request.name(), request.price(), request.discountpolicy());
        productPort.save(product);
    }
}
