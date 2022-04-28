package org.hy.pizza.exception;

public class PizzaNotFoundException extends RuntimeException {
    public PizzaNotFoundException(Long id) {
        super("Not found pizza ID " + id);
    }
}
