package org.hy.pizza.repository;

import org.hy.pizza.model.PizzaOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaOrderRepository extends JpaRepository<PizzaOrder, Long> {
}
