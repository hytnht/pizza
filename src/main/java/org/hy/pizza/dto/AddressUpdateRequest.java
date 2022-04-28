package org.hy.pizza.dto;

import org.hy.pizza.model.City;

public record AddressUpdateRequest(
        String unit,
        String street,
        String ward,
        String district,
        City city
) {
}
