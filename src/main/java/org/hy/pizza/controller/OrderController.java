package org.hy.pizza.controller;

import org.aspectj.weaver.ast.Or;
import org.hy.pizza.assembler.OrderAssembler;
import org.hy.pizza.model.Order;
import org.hy.pizza.repository.OrderRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OrderController {
    private final OrderAssembler assembler;
    private final OrderRepository repository;

    public OrderController(OrderAssembler assembler, OrderRepository repository) {
        this.assembler = assembler;
        this.repository = repository;
    }

    @GetMapping("/orders")
    public CollectionModel<EntityModel<Order>> all() {
        List<EntityModel<Order>> orders = repository.findAll().stream().map(assembler::toModel).toList();
        return CollectionModel.of(orders, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @GetMapping("/order/{id}")
    public EntityModel<Order>
}
