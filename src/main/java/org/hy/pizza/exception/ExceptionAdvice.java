package org.hy.pizza.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {
    @ResponseBody
    @ExceptionHandler({CustomerNotFoundException.class, OrderNotFoundException.class, PizzaNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<?> notFoundHandler(RuntimeException exception) {
        return ResponseEntity.status(404).body(exception.getMessage());
    }
}
