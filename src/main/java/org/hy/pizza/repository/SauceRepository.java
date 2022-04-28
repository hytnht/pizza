package org.hy.pizza.repository;

import org.hy.pizza.model.Sauce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SauceRepository extends JpaRepository<Sauce, Long> {
}
