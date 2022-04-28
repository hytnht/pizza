package org.hy.pizza.repository;

import org.hy.pizza.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
