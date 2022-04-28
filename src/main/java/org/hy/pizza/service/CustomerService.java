package org.hy.pizza.service;

import org.hy.pizza.model.Customer;
import org.hy.pizza.payload.CustomerCreateRequest;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    Customer create(CustomerCreateRequest newCus);
    Customer update();
    void deleteById(Long id);

}
