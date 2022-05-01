package org.hy.pizza.assembler;

import org.hy.pizza.controller.AddressController;
import org.hy.pizza.model.Address;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AddressAssembler implements RepresentationModelAssembler<Address, EntityModel<Address>> {
    @Override
    public EntityModel<Address> toModel(Address address) {
        return EntityModel.of(address,
                linkTo(methodOn(AddressController.class).one(address.getId())).withSelfRel(),
                linkTo(methodOn(AddressController.class).all()).withRel("addresses"));
    }
}
