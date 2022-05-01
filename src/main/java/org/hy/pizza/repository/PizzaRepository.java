package org.hy.pizza.repository;

import org.hy.pizza.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
