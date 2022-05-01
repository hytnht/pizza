package org.hy.pizza.service;

import lombok.RequiredArgsConstructor;
import org.hy.pizza.assembler.CrustAssembler;
import org.hy.pizza.exception.IngredientNotFoundException;
import org.hy.pizza.model.Crust;
import org.hy.pizza.repository.CrustRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CrustServiceImpl implements CrustService {
    private final CrustRepository crustRepository;

    @Override
    public List<Crust> findAll() {
          return crustRepository.findAll();
    }

    @Override
    public Crust findById(Long id) {
        return crustRepository.findById(id).orElseThrow(() -> new IngredientNotFoundException(id));
    }
}
