package org.hy.pizza.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("Not found customer ID " + id);
    }
}
