package org.hy.pizza.dto;

import org.hy.pizza.model.City;
import org.hy.pizza.model.Customer;

import javax.validation.constraints.NotNull;

public record AddressCreateRequest (
        @NotNull Customer customer,
        String unit,
        @NotNull String street,
        @NotNull String ward,
        @NotNull String district,
        @NotNull City city
) {
}
