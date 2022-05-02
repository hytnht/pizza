package org.hy.pizza.assembler;

import org.hy.pizza.controller.ToppingController;
import org.hy.pizza.model.Topping;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ToppingAssembler implements RepresentationModelAssembler<Topping, EntityModel<Topping>> {
    @Override
    public EntityModel<Topping> toModel(Topping topping) {
        return EntityModel.of(topping,
                linkTo(methodOn(ToppingController.class).one(topping.getId())).withSelfRel(),
                linkTo(methodOn(ToppingController.class).all()).withRel("toppings"));
    }
}
