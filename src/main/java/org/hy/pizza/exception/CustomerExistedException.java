package org.hy.pizza.exception;

public class CustomerExistedException extends RuntimeException {
    public CustomerExistedException(String phone) {
        super("Phone number " + phone + "is already existed.");
    }

    public CustomerExistedException(Long id) {
        super("Customer ID " + id + "is already existed.");
    }
}
