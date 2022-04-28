package org.hy.pizza.service;

import org.hy.pizza.model.Customer;
import org.hy.pizza.dto.CustomerCreateRequest;
import org.hy.pizza.dto.CustomerUpdateRequest;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    Customer create(CustomerCreateRequest request);
    void deleteById(Long id);
    Customer update(Long id, CustomerUpdateRequest request);
}
