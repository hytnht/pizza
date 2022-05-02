package org.hy.pizza.controller;

import lombok.RequiredArgsConstructor;
import org.hy.pizza.assembler.OrderAssembler;
import org.hy.pizza.dto.OrderCreateRequest;
import org.hy.pizza.model.Order;
import org.hy.pizza.service.OrderService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderAssembler assembler;
    private final OrderService service;

    @GetMapping("/v1/order")
    public ResponseEntity<CollectionModel<EntityModel<Order>>> all() {
        List<EntityModel<Order>> orders = service.findAll().stream().map(assembler::toModel).toList();
        return ResponseEntity.ok().body(
                CollectionModel.of(orders, linkTo(methodOn(OrderController.class).all()).withSelfRel()));
    }

    @GetMapping("/v1/order/{id}")
    public ResponseEntity<EntityModel<Order>> one(@PathVariable Long id) {
        EntityModel<Order> order = assembler.toModel(service.findById(id));
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/v1/order")
    public ResponseEntity<EntityModel<Order>> create(@RequestBody OrderCreateRequest request) {
        EntityModel<Order> model = assembler.toModel(service.create(request));
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }

    @DeleteMapping("/v1/order/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
