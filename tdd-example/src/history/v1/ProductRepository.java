package org.example.tddexample.product;

import java.util.HashMap;
import java.util.Map;

class ProductRepository {
    private Map<Long, Product> persistence = new HashMap<>();
    private Long sequence = 0L;

    public void save(final Product product) {
        product.assignId(++sequence);
        persistence.put(product.getId(), product);
    }
}
