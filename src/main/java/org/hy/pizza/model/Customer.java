package org.hy.pizza.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@GenericGenerator(
        name = "seq_generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {@Parameter(name = "sequence_name", value = "customer_seq")})
@Table(name = "customer")
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
    private Long id;

    @Length(max = 50)
    @NotNull
    private String name;

    @Column(name = "day_of_birth")
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private LocalDate dob;

    @Column(name = "phone_number", unique = true, length = 15)
    @NotNull
    @Length(min = 7, max = 15)
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @ToString.Exclude
    private List<Order> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @ToString.Exclude
    private List<CustomerAddress> customerAddress;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return id != null && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
