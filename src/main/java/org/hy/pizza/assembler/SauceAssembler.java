package org.hy.pizza.assembler;

import org.hy.pizza.controller.SauceController;
import org.hy.pizza.model.Sauce;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class SauceAssembler implements RepresentationModelAssembler<Sauce, EntityModel<Sauce>> {
    @Override
    public EntityModel<Sauce> toModel(Sauce sauce) {
        return EntityModel.of(sauce,
                linkTo(methodOn(SauceController.class).one(sauce.getId())).withSelfRel(),
                linkTo(methodOn(SauceController.class).all()).withRel("sauces"));
    }
}
