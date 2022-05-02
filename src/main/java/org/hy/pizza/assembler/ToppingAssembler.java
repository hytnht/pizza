package org.hy.pizza.assembler;

import org.hy.pizza.controller.ToppingController;
import org.hy.pizza.model.Topping;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ToppingAssembler implements RepresentationModelAssembler<Topping, EntityModel<Topping>> {
    @Override
    @NonNull
    public EntityModel<Topping> toModel(@NonNull Topping topping) {
        return EntityModel.of(topping,
                linkTo(methodOn(ToppingController.class).one(topping.getId())).withSelfRel(),
                linkTo(methodOn(ToppingController.class).all()).withRel("topping"));
    }
}
