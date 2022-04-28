package org.hy.pizza.controller;

import org.hy.pizza.assembler.CustomerAssembler;
import org.hy.pizza.exception.CustomerNotFoundException;
import org.hy.pizza.model.Customer;
import org.hy.pizza.repository.CustomerRepository;
import org.hy.pizza.service.CustomerService;
import org.hy.pizza.service.CustomerServiceImpl;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CustomerController {
    private final CustomerRepository repository;
    private final CustomerAssembler assembler;
    private final CustomerServiceImpl service;

    public CustomerController(CustomerRepository repository, CustomerAssembler assembler, CustomerServiceImpl service) {
        this.repository = repository;
        this.assembler = assembler;
        this.service = service;
    }

    @GetMapping("/customers")
    public ResponseEntity<CollectionModel<EntityModel<Customer>>> all() {
        List<EntityModel<Customer>> customers = service.findAll().stream().map(assembler::toModel).toList();
        return ResponseEntity.ok().body(
                CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).all()).withSelfRel()));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<EntityModel<Customer>> one(@PathVariable Long id) {
        EntityModel<Customer> customer = assembler.toModel(service.findById(id));
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/customers")
    public ResponseEntity<EntityModel<Customer>> add(@RequestBody Customer customer) {
        EntityModel<Customer> model = assembler.toModel(repository.save(customer));
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<EntityModel<Customer>> update(@RequestBody Customer newCus, @PathVariable Long id) {
        Customer updateCus = repository.findById(id).map(customer -> {
            customer.setName(newCus.getName());
            customer.setDob(newCus.getDob());
            customer.setPhone(newCus.getPhone());
            return repository.save(customer);
        }).orElseGet(() -> {
            newCus.setId(id);
            return repository.save(newCus);
        });
        EntityModel<Customer> model = assembler.toModel(updateCus);
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}