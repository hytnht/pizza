package org.hy.pizza.dto;

import org.hy.pizza.model.Size;

import javax.validation.constraints.NotNull;
import java.util.List;

public record OrderCreateRequest(
        @NotNull Long customerID,
        @NotNull Long addressID,
        @NotNull List<Pizza> pizza,
        String note
) {public record Pizza(
        @NotNull Long crustID,
        @NotNull Size size,
        @NotNull List<Long> toppingID,
        @NotNull List<Long> sauceID,
        @NotNull Integer quantity
) {
}
}
