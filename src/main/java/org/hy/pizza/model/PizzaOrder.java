package org.hy.pizza.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "pizza_order")
@IdClass(PizzaOrderID.class)
@Entity
public class PizzaOrder {
    @ManyToOne
    @JoinColumn(name = "pizza_id")
    @NotNull
    @Id
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @NotNull
    @Id
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PizzaOrder that = (PizzaOrder) o;
        return pizza != null && Objects.equals(pizza, that.pizza)
                && order != null && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizza, order);
    }
}
