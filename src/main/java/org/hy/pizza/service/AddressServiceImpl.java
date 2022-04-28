package org.hy.pizza.service;

import org.hy.pizza.dto.AddressCreateRequest;
import org.hy.pizza.dto.AddressUpdateRequest;
import org.hy.pizza.exception.AddressNotFoundException;
import org.hy.pizza.mapper.AddressMapper;
import org.hy.pizza.model.Address;
import org.hy.pizza.model.CustomerAddress;
import org.hy.pizza.repository.AddressRepository;
import org.hy.pizza.repository.CustomerAddressRepository;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final CustomerAddressRepository customerAddressRepository;

    public AddressServiceImpl(AddressRepository addressRepository, CustomerAddressRepository customerAddressRepository) {
        this.addressRepository = addressRepository;
        this.customerAddressRepository = customerAddressRepository;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
    }

    @Override
    public Address create(AddressCreateRequest request) {
        Address address = addressRepository.save(AddressMapper.INSTANCE.toAddress(request));
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setCustomer(request.customer());
        customerAddress.setAddress(address);
        customerAddressRepository.save(customerAddress);
        return address;
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Address update(Long id, AddressUpdateRequest request) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address == null) {
            return create(AddressMapper.INSTANCE.toAddressCreate(request));
        }
        AddressMapper.INSTANCE.toAddress(request, address);
        return addressRepository.save(address);
    }
}
