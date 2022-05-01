package org.hy.pizza.repository;

import org.hy.pizza.model.PizzaSauce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaSauceRepository extends JpaRepository<PizzaSauce, Long> {
}
