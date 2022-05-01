package org.hy.pizza.exception;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(Long id) {
        super("Not found ingredient ID " + id);
    }
}
