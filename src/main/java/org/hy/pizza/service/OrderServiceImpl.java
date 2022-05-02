package org.hy.pizza.service;

import lombok.RequiredArgsConstructor;
import org.hy.pizza.dto.OrderCreateRequest;
import org.hy.pizza.exception.IngredientNotFoundException;
import org.hy.pizza.exception.OrderNotFoundException;
import org.hy.pizza.mapper.OrderMapper;
import org.hy.pizza.mapper.PizzaMapper;
import org.hy.pizza.model.Crust;
import org.hy.pizza.model.Order;
import org.hy.pizza.model.Pizza;
import org.hy.pizza.model.PizzaOrder;
import org.hy.pizza.model.PizzaSauce;
import org.hy.pizza.model.PizzaTopping;
import org.hy.pizza.model.Sauce;
import org.hy.pizza.model.Topping;
import org.hy.pizza.repository.CrustRepository;
import org.hy.pizza.repository.OrderRepository;
import org.hy.pizza.repository.PizzaOrderRepository;
import org.hy.pizza.repository.PizzaRepository;
import org.hy.pizza.repository.PizzaSauceRepository;
import org.hy.pizza.repository.PizzaToppingRepository;
import org.hy.pizza.repository.SauceRepository;
import org.hy.pizza.repository.ToppingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final PizzaRepository pizzaRepository;
    private final OrderRepository orderRepository;
    private final PizzaOrderRepository pizzaOrderRepository;
    private final CrustRepository crustRepository;
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
        Order order = OrderMapper.INSTANCE.toOrder(request);
        order.setCustomer(customerService.findById(request.customerID()));
        order.setAddress(addressService.findById(request.addressID()));
        orderRepository.save(order);
        Map<Long, Crust> crustMap = crustRepository.findAllById(request.pizza().stream()
                        .map(OrderCreateRequest.Pizza::crustID)
                        .distinct()
                        .collect(Collectors.toList())).stream()
                .collect(Collectors.toMap(Crust::getId, c -> c));

        List<Pizza> pizzas = pizzaRepository.saveAll(request.pizza().stream().map(p -> {
            Pizza pizza = PizzaMapper.INSTANCE.toPizza(p);
            if (!crustMap.containsKey(p.crustID())) {
                throw new IngredientNotFoundException(p.crustID());
            }
            pizza.setCrust(crustMap.get(p.crustID()));
            return pizza;
        }).collect(Collectors.toList()));

        Set<Long> toppingIDs = new HashSet<>();
        request.pizza().forEach(p -> toppingIDs.addAll(p.toppingID()));
        Map<Long, Topping> toppingMap = toppingRepository.findAllById(toppingIDs)
                .stream().collect(Collectors.toMap(Topping::getId, t -> t));

        Set<Long> sauceIDs = new HashSet<>();
        request.pizza().forEach(p -> sauceIDs.addAll(p.sauceID()));
        Map<Long, Sauce> sauceMap = sauceRepository.findAllById(sauceIDs)
                .stream().collect(Collectors.toMap(Sauce::getId, t -> t));

        List<PizzaTopping> pizzaToppings = new ArrayList<>();
        List<PizzaSauce> pizzaSauces = new ArrayList<>();
        IntStream.range(0, pizzas.size()).forEach(i -> {
            pizzaToppings.addAll(request.pizza().get(i).toppingID().stream().map(t -> {
                PizzaTopping pizzaTopping = new PizzaTopping();
                pizzaTopping.setPizza(pizzas.get(i));
                if (!toppingMap.containsKey(t)) {
                    throw new IngredientNotFoundException(t);
                }
                pizzaTopping.setTopping(toppingMap.get(t));
                return pizzaTopping;
            }).toList());

            pizzaSauces.addAll(request.pizza().get(i).sauceID().stream().map(s -> {
                PizzaSauce pizzaSauce = new PizzaSauce();
                pizzaSauce.setPizza(pizzas.get(i));
                if (!sauceMap.containsKey(s)) {
                    throw new IngredientNotFoundException(s);
                }
                pizzaSauce.setSauce(sauceMap.get(s));
                return pizzaSauce;
            }).toList());
        });

        pizzaToppingRepository.saveAll(pizzaToppings);
        pizzaSauceRepository.saveAll(pizzaSauces);

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
