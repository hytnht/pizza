package org.hy.pizza.mapper;

import org.hy.pizza.dto.AddressCreateRequest;
import org.hy.pizza.dto.AddressUpdateRequest;
import org.hy.pizza.dto.CustomerCreateRequest;
import org.hy.pizza.dto.CustomerUpdateRequest;
import org.hy.pizza.model.Address;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address toAddress(CustomerCreateRequest.Address request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address toAddress(AddressCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toAddress(AddressUpdateRequest request, @MappingTarget Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AddressCreateRequest toAddressCreate(AddressUpdateRequest request);
}
