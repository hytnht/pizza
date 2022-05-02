package org.hy.pizza.controller;

import lombok.RequiredArgsConstructor;
import org.hy.pizza.assembler.CustomerAssembler;
import org.hy.pizza.model.Customer;
import org.hy.pizza.dto.CustomerCreateRequest;
import org.hy.pizza.dto.CustomerUpdateRequest;
import org.hy.pizza.service.CustomerService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CustomerController {
    private final CustomerAssembler assembler;
    private final CustomerService service;

    @GetMapping("/v1/customer")
    public ResponseEntity<CollectionModel<EntityModel<Customer>>> all() {
        List<EntityModel<Customer>> customers = service.findAll().stream().map(assembler::toModel).toList();
        return ResponseEntity.ok().body(CollectionModel.of(customers));
    }

    @GetMapping("/v1/customer/{id}")
    public ResponseEntity<EntityModel<Customer>> one(@PathVariable Long id) {
        EntityModel<Customer> customer = assembler.toModel(service.findById(id));
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/v1/customer")
    public ResponseEntity<EntityModel<Customer>> create(@RequestBody CustomerCreateRequest customer) {
        EntityModel<Customer> model = assembler.toModel(service.create(customer));
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }

    @PutMapping("/v1/customer/{id}")
    public ResponseEntity<EntityModel<Customer>> update(  CustomerUpdateRequest request, @PathVariable Long id) {
        Customer customer = service.update(id, request);
        EntityModel<Customer> model = assembler.toModel(customer);
        return ResponseEntity.ok().body(model);
    }

    @DeleteMapping("/v1/customer/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
