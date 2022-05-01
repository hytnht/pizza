package org.hy.pizza.service;

import org.hy.pizza.model.Topping;

import java.util.List;

public interface ToppingService {
    List<Topping> findAll();
    Topping findById(Long id);
}
