package org.hy.pizza.service;

import lombok.RequiredArgsConstructor;
import org.hy.pizza.exception.IngredientNotFoundException;
import org.hy.pizza.model.Sauce;
import org.hy.pizza.repository.SauceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SauceServiceImpl implements SauceService {
    private final SauceRepository sauceRepository;

    @Override
    public List<Sauce> findAll() {
          return sauceRepository.findAll();
    }

    @Override
    public Sauce findById(Long id) {
        return sauceRepository.findById(id).orElseThrow(() -> new IngredientNotFoundException(id));
    }
}
