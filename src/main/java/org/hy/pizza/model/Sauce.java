package org.hy.pizza.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

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
@Table(name = "sauce")
@Entity
public class Sauce {
    private @Id
    @GeneratedValue
    Long id;

    @NotNull
    private String name;

    @NotNull
    private Float price;

    @NotNull
    private Integer stock;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sauce")
    private List<PizzaAddon> addon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sauce sauce = (Sauce) o;
        return id != null && Objects.equals(id, sauce.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
