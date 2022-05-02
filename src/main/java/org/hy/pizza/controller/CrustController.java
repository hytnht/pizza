package org.hy.pizza.controller;

import lombok.RequiredArgsConstructor;
import org.hy.pizza.assembler.CrustAssembler;
import org.hy.pizza.model.Crust;
import org.hy.pizza.service.CrustService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CrustController {
    private final CrustAssembler assembler;
    private final CrustService service;

    @GetMapping("/v1/crust")
    public ResponseEntity<CollectionModel<EntityModel<Crust>>> all() {
        List<EntityModel<Crust>> crusts = service.findAll().stream().map(assembler::toModel).toList();
        return ResponseEntity.ok().body(
                CollectionModel.of(crusts, linkTo(methodOn(CrustController.class).all()).withSelfRel()));
    }

    @GetMapping("/v1/crust/{id}")
    public ResponseEntity<EntityModel<Crust>> one(@PathVariable Long id) {
        return ResponseEntity.ok().body(assembler.toModel(service.findById(id)));
    }
}
