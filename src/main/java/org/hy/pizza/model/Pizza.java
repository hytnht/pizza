package org.hy.pizza.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "pizza")
@Entity
public class Pizza {
    @NotNull
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "quantity")
    @NotNull
    private Integer qty;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Size size;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Crust crust;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pizza")
    @ToString.Exclude
    private List<PizzaOrder> pizzaOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pizza pizza = (Pizza) o;
        return id != null && Objects.equals(id, pizza.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
