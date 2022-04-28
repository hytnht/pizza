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
@IdClass(CustomerAddressID.class)
@Table(name = "customer_address")
@Entity
public class CustomerAddress {
    @ManyToOne
    @NotNull
    @Id
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @NotNull
    @Id
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CustomerAddress that = (CustomerAddress) o;
        return customer != null && Objects.equals(customer, that.customer)
                && address != null && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, address);
    }
}
