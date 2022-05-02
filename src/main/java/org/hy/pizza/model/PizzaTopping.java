package org.hy.pizza.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "pizza_topping")
@IdClass(PizzaToppingID.class)
@Entity
public class PizzaTopping {
    @ManyToOne
    @JoinColumn(name = "pizza_id")
    @NotNull
    @Id
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "topping_id")
    @NotNull
    @Id
    private Topping topping;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PizzaTopping that = (PizzaTopping) o;
        return pizza != null && Objects.equals(pizza, that.pizza)
                && topping != null && Objects.equals(topping, that.topping);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizza, topping);
    }
}
