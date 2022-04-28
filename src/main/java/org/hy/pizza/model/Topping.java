package org.hy.pizza.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "topping")
@Entity
public class Topping {
    private @Id
    @GeneratedValue
    Long id;

    @NotNull
    private String name;

    @NotNull
    private Float price;

    @NotNull
    private Integer stock;

    @Column(name = "quantity")
    @NotNull
    private Integer qty;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topping")
    private List<PizzaAddon> addon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Topping topping = (Topping) o;
        return id != null && Objects.equals(id, topping.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
