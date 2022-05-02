package org.hy.pizza.assembler;

import org.hy.pizza.controller.CrustController;
import org.hy.pizza.model.Crust;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CrustAssembler implements RepresentationModelAssembler<Crust, EntityModel<Crust>> {
    @Override
    public EntityModel<Crust> toModel(Crust crust) {
        return EntityModel.of(crust,
                linkTo(methodOn(CrustController.class).one(crust.getId())).withSelfRel(),
                linkTo(methodOn(CrustController.class).all()).withRel("crusts"));
    }
}
