package org.hy.pizza.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Not found order ID " + id);
    }
}
