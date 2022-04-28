package org.hy.pizza.repository;

import org.hy.pizza.model.PizzaAddon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaAddonRepository extends JpaRepository<PizzaAddon, Long> {
}
