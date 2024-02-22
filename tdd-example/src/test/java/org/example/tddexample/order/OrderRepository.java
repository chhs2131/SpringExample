package org.example.tddexample.order;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository {
    private final Map<Long, Order> persistence = new HashMap<>();
    private Long sequence = 0L;

    public void save(final Order order) {
        order.assignId(++sequence);
        persistence.put(order.getId(), order);
    }
}
