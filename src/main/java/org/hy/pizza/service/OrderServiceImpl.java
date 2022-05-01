package org.hy.pizza.service;

import lombok.RequiredArgsConstructor;
import org.hy.pizza.dto.OrderCreateRequest;
import org.hy.pizza.exception.OrderNotFoundException;
import org.hy.pizza.mapper.OrderMapper;
import org.hy.pizza.mapper.PizzaMapper;
import org.hy.pizza.model.Order;
import org.hy.pizza.model.Pizza;
import org.hy.pizza.model.PizzaOrder;
import org.hy.pizza.model.PizzaSauce;
import org.hy.pizza.model.PizzaTopping;
import org.hy.pizza.repository.OrderRepository;
import org.hy.pizza.repository.PizzaOrderRepository;
import org.hy.pizza.repository.PizzaRepository;
import org.hy.pizza.repository.PizzaSauceRepository;
import org.hy.pizza.repository.PizzaToppingRepository;
import org.hy.pizza.repository.SauceRepository;
import org.hy.pizza.repository.ToppingRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final PizzaRepository pizzaRepository;
    private final OrderRepository orderRepository;
    private final PizzaOrderRepository pizzaOrderRepository;
    private final ToppingRepository toppingRepository;
    private final PizzaToppingRepository pizzaToppingRepository;
    private final PizzaSauceRepository pizzaSauceRepository;
    private final SauceRepository sauceRepository;
    private final CustomerService customerService;
    private final AddressService addressService;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public Order create(OrderCreateRequest request) {
        Order order = orderRepository.save(OrderMapper.INSTANCE.toOrder(request));
        order.setCustomer(customerService.findById(request.customerID()));
        order.setAddress(addressService.findById(request.addressID()));

        List<Pizza> pizzas = pizzaRepository.saveAll(request.pizza().stream().map(eachPizza -> {
            Pizza pizza = PizzaMapper.INSTANCE.toPizza(eachPizza);

            pizzaToppingRepository.saveAll(toppingRepository.findAllById(eachPizza.toppingID()).stream().map((topping) -> {
                PizzaTopping pizzaTopping = new PizzaTopping();
                pizzaTopping.setPizza(pizza);
                pizzaTopping.setTopping(topping);
                return pizzaTopping;
            }).collect(Collectors.toList()));

            pizzaSauceRepository.saveAll(sauceRepository.findAllById(eachPizza.sauceID()).stream().map((sauce) -> {
                PizzaSauce pizzaSauce = new PizzaSauce();
                pizzaSauce.setPizza(pizza);
                pizzaSauce.setSauce(sauce);
                return pizzaSauce;
            }).collect(Collectors.toList()));

            return pizza;
        }).collect(Collectors.toList()));

        pizzaOrderRepository.saveAll(pizzas.stream().map(pizza -> {
            PizzaOrder pizzaOrder = new PizzaOrder();
            pizzaOrder.setOrder(order);
            pizzaOrder.setPizza(pizza);
            return pizzaOrder;
        }).collect(Collectors.toList()));

        return order;
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
