package org.hy.pizza.mapper;

import org.hy.pizza.dto.OrderCreateRequest;
import org.hy.pizza.model.Pizza;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PizzaMapper {
    PizzaMapper INSTANCE = Mappers.getMapper(PizzaMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Pizza toPizza(OrderCreateRequest.Pizza request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toPizza(OrderCreateRequest.Pizza request, @MappingTarget Pizza pizza);
}
