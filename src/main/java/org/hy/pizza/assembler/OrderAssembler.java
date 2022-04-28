//package org.hy.pizza.assembler;
//
//import org.hy.pizza.controller.OrderController;
//import org.hy.pizza.model.Order;
//import org.springframework.hateoas.CollectionModel;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.RepresentationModel;
//import org.springframework.hateoas.server.RepresentationModelAssembler;
//
//
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//
//public class OrderAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {
//    @Override
//    public EntityModel<Order> toModel(Order order) {
//        return EntityModel.of(order,
//                linkTo(methodOn(OrderController.class).one()).withSelfRel(),
//                linkTo(methodOn(OrderController.class).all()).withRel("orders"));
//    }
//
//}
