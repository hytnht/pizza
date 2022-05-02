package org.hy.pizza.mapper;

import org.hy.pizza.model.Customer;
import org.hy.pizza.dto.CustomerCreateRequest;
import org.hy.pizza.dto.CustomerUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "birthday", target = "dob")
    Customer toCustomer(CustomerCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "birthday", target = "dob")
    void toCustomer(CustomerUpdateRequest request, @MappingTarget Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CustomerCreateRequest toCustomerCreate(CustomerUpdateRequest request);

}
