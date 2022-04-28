package org.hy.pizza.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "address")
@Entity
public class Address {
    private @Id
    @GeneratedValue
    Long id;

    private String unit;

    @NotNull
    @Column(nullable = false)
    private String street;

    @NotNull
    @Column(nullable = false, length = 30)
    private String ward;

    @NotNull
    @Column(nullable = false, length = 30)
    private String district;

    @NotNull
    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private City city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
    @ToString.Exclude
    private List<CustomerAddress> customerAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;
        return id != null && Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
