package org.hy.pizza.controller;

import lombok.RequiredArgsConstructor;
import org.hy.pizza.assembler.SauceAssembler;
import org.hy.pizza.model.Sauce;
import org.hy.pizza.service.SauceService;
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
public class SauceController {
    private final SauceAssembler assembler;
    private final SauceService service;
    
    @GetMapping("/v1/sauce")
    public ResponseEntity<CollectionModel<EntityModel<Sauce>>> all() {
        List<EntityModel<Sauce>> crusts = service.findAll().stream().map(assembler::toModel).toList(); 
        return ResponseEntity.ok().body(
                CollectionModel.of(crusts, linkTo(methodOn(SauceController.class).all()).withSelfRel()));
    }
    
    @GetMapping("/v1/sauce/{id}")
    public ResponseEntity<EntityModel<Sauce>> one(@PathVariable Long id) {
        return ResponseEntity.ok().body(assembler.toModel(service.findById(id)));
    } 
}
