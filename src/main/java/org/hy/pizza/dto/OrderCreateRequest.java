package org.hy.pizza.dto;

import org.hy.pizza.model.Size;

import javax.validation.constraints.NotNull;

public record OrderCreateRequest(
        @NotNull String customer,
        @NotNull String crust,
        @NotNull String topping,
        @NotNull String sauce,
        @NotNull int quantity,
        @NotNull Size size,

        String note
) {
}
