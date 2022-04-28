package org.hy.pizza.service;

import org.hy.pizza.dto.AddressCreateRequest;
import org.hy.pizza.dto.AddressUpdateRequest;
import org.hy.pizza.model.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAll();
    Address findById(Long id);
    Address create(AddressCreateRequest request);
    void deleteById(Long id);
    Address update(Long id, AddressUpdateRequest request);
}
