package org.hy.pizza.model;

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
@Table(name = "pizza_addon")
@IdClass(PizzaAddonID.class)
@Entity
public class PizzaAddon {
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

    @ManyToOne
    @JoinColumn(name = "sauce_id")
    @NotNull
    @Id
    private Sauce sauce;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PizzaAddon that = (PizzaAddon) o;
        return pizza != null && Objects.equals(pizza, that.pizza)
                && topping != null && Objects.equals(topping, that.topping)
                && sauce != null && Objects.equals(sauce, that.sauce);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizza, topping, sauce);
    }
}
