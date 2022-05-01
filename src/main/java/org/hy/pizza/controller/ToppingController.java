package org.hy.pizza.controller;

import lombok.RequiredArgsConstructor;
import org.hy.pizza.assembler.ToppingAssembler;
import org.hy.pizza.model.Topping;
import org.hy.pizza.service.ToppingService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
public class ToppingController {
    private final ToppingAssembler assembler;
    private final ToppingService service;
    
    @GetMapping("/crusts")
    public ResponseEntity<CollectionModel<EntityModel<Topping>>> all() {
        List<EntityModel<Topping>> crusts = service.findAll().stream().map(assembler::toModel).toList(); 
        return ResponseEntity.ok().body(
                CollectionModel.of(crusts, linkTo(methodOn(ToppingController.class).all()).withSelfRel()));
    }
    
    @GetMapping("/crust/{id}")
    public ResponseEntity<EntityModel<Topping>> one(@PathVariable Long id) {
        return ResponseEntity.ok().body(assembler.toModel(service.findById(id)));
    } 
}
