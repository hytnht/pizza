package org.hy.pizza.dto;

public record OrderUpdateRequest(
        Long customer,
        Long pizza,
        String note
) {
}
