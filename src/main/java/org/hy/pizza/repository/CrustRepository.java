package org.hy.pizza.repository;

import org.hy.pizza.model.Crust;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrustRepository extends JpaRepository<Crust, Long> {
}
