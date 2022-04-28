package org.hy.pizza.service;

import lombok.RequiredArgsConstructor;
import org.hy.pizza.exception.CustomerExistedException;
import org.hy.pizza.exception.CustomerNotFoundException;
import org.hy.pizza.mapper.AddressMapper;
import org.hy.pizza.mapper.CustomerMapper;
import org.hy.pizza.model.Address;
import org.hy.pizza.model.Customer;
import org.hy.pizza.dto.CustomerCreateRequest;
import org.hy.pizza.dto.CustomerUpdateRequest;
import org.hy.pizza.model.CustomerAddress;
import org.hy.pizza.repository.AddressRepository;
import org.hy.pizza.repository.CustomerAddressRepository;
import org.hy.pizza.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final CustomerAddressRepository customerAddressRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    @Transactional
    public Customer create(CustomerCreateRequest request) {
        Optional<Customer> existedCustomer = customerRepository.findByPhone(request.phone());
        if (existedCustomer.isPresent()) {
            throw new CustomerExistedException(request.phone());
        }

        Customer customer = customerRepository.save(CustomerMapper.INSTANCE.toCustomer(request));
        Address address = addressRepository.save(AddressMapper.INSTANCE.toAddress(request.address()));
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setCustomer(customer);
        customerAddress.setAddress(address);
        customerAddressRepository.save(customerAddress);
        return customer;
    }

    @Override
    public Customer update(Long id, CustomerUpdateRequest request) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) return create(CustomerMapper.INSTANCE.toCustomerCreate(request));
        CustomerMapper.INSTANCE.toCustomer(request, customer);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
