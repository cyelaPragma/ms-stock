package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.mapper;

import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.ShoppingCartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShoppingCartMapper {

    @Mapping(source = "user.id", target = "userId")
    ShoppingCartEntity shoppingCartToShoppingCartEntity(ShoppingCart shoppingCart);

    @Mapping(source = "userId", target = "user.id")
    ShoppingCart shoppingCartEntityToShoppingCart(ShoppingCartEntity shoppingCartEntity);
}
