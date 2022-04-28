package org.hy.pizza.dto;

import org.hy.pizza.model.City;

import java.util.Date;

public record CustomerUpdateRequest(
        String name,
        Date birthday,
        String phone,
        Address address
) {
    public record Address(
            String unit,
            String street,
            String ward,
            String district,
            City city
    ) {
    }
}
