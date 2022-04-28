package org.hy.pizza.service;

import org.hy.pizza.exception.CustomerNotFoundException;
import org.hy.pizza.model.Customer;
import org.hy.pizza.payload.CustomerCreateRequest;
import org.hy.pizza.repository.CustomerRepository;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public Customer create(CustomerCreateRequest customer) {
        return repository.save(customer);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
