package org.hy.pizza.payload;

import javax.validation.constraints.NotNull;
import java.util.Date;

public record CustomerCreateRequest(@NotNull String name, Date dob, @NotNull String phone, String address) {
}
