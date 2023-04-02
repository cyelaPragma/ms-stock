package com.acelera.ti.stock.domain.model.model.cart;

import com.acelera.ti.stock.domain.model.model.user.Person;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class ShoppingCart {
    private Long id;
    private Person user;
    private Set<ShoppingCartProduct> products;
    private LocalDate lastUpdate;
}
