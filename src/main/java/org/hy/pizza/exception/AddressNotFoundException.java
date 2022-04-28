package org.hy.pizza.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Long id) {
        super("Not found address ID " + id);
    }
}
