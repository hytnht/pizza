package org.hy.pizza.model;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class PizzaOrderID implements Serializable {
    private Long pizza;
    private Long order;
}
