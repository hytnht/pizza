package org.hy.pizza.controller;

import lombok.RequiredArgsConstructor;
import org.hy.pizza.assembler.AddressAssembler;
import org.hy.pizza.dto.AddressCreateRequest;
import org.hy.pizza.dto.AddressUpdateRequest;
import org.hy.pizza.model.Address;
import org.hy.pizza.repository.AddressRepository;
import org.hy.pizza.service.AddressService;
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
public class AddressController {
    private final AddressAssembler assembler;
    private final AddressService service;

    @GetMapping("/v1/address")
    public ResponseEntity<CollectionModel<EntityModel<Address>>> all() {
        List<EntityModel<Address>> address = service.findAll().stream().map(assembler::toModel).toList();
        return ResponseEntity.ok().body(
                CollectionModel.of(address, linkTo(methodOn(AddressController.class).all()).withSelfRel()));
    }

    @GetMapping("/v1/address/{id}")
    public ResponseEntity<EntityModel<Address>> one(@PathVariable Long id) {
        EntityModel<Address> address = assembler.toModel(service.findById(id));
        return ResponseEntity.ok().body(address);
    }

    @PostMapping("/v1/address")
    public ResponseEntity<EntityModel<Address>> create(@RequestBody AddressCreateRequest request) {
        EntityModel<Address> address = assembler.toModel(service.create(request));
        return ResponseEntity.created(address.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(address);
    }

    @PutMapping("/v1/address/{id}")
    public ResponseEntity<EntityModel<Address>> update(@RequestBody AddressUpdateRequest request, @PathVariable Long id) {
        EntityModel<Address> address = assembler.toModel(service.update(id, request));
        return ResponseEntity.ok().body(address);
    }

    @DeleteMapping("/v1/address/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
