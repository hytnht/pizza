package org.hy.pizza.repository;

import org.hy.pizza.model.PizzaTopping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaToppingRepository extends JpaRepository<PizzaTopping, Long> {
}
