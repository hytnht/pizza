package org.hy.pizza.mapper;

import org.hy.pizza.dto.OrderCreateRequest;
import org.hy.pizza.model.Order;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "customerID", target = "customer", ignore = true)
    @Mapping(source = "addressID", target = "address", ignore = true)
    Order toOrder(OrderCreateRequest request);
}
