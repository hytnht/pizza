package org.hy.pizza.service;

import lombok.RequiredArgsConstructor;
import org.hy.pizza.exception.IngredientNotFoundException;
import org.hy.pizza.model.Topping;
import org.hy.pizza.repository.ToppingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ToppingServiceImpl implements ToppingService {
    private final ToppingRepository toppingRepository;

    @Override
    public List<Topping> findAll() {
          return toppingRepository.findAll();
    }

    @Override
    public Topping findById(Long id) {
        return toppingRepository.findById(id).orElseThrow(() -> new IngredientNotFoundException(id));
    }
}
