package org.hy.pizza.repository;

import org.hy.pizza.model.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {
}
