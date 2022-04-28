package org.hy.pizza.dto;

import org.hy.pizza.model.City;

import javax.validation.constraints.NotNull;
import java.util.Date;

public record CustomerCreateRequest(
        @NotNull String name,
        Date birthday,
        @NotNull String phone,
        @NotNull Address address
) {
    public record Address(
            String unit,
            @NotNull String street,
            @NotNull String ward,
            @NotNull String district,
            @NotNull City city
    ) {
    }
}
