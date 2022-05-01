package org.hy.pizza.model;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class PizzaSauceID implements Serializable {
    private Long pizza;
    private Long sauce;
}
