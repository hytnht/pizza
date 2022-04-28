package org.hy.pizza.model;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class CustomerAddressID implements Serializable {
    private Long customer;
    private Long address;
}
