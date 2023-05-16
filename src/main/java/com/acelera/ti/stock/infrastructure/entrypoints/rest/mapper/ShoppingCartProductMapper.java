package com.acelera.ti.stock.infrastructure.entrypoints.rest.mapper;

import com.acelera.ti.stock.domain.model.model.cart.ShoppingCartProduct;
import com.acelera.ti.stock.infrastructure.entrypoints.rest.dto.ShoppingCartProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ShoppingCartProductMapper {
    Set<ShoppingCartProductDto> shoppingCartProductToShoppingCartProductDto(
            Set<ShoppingCartProduct> shoppingCartProduct);

    @Mapping(target = "name", source = "shoppingCartProduct.stock.product.name")
    @Mapping(target = "description", source = "shoppingCartProduct.stock.product.description")
    @Mapping(target = "sellPrice", source = "shoppingCartProduct.stock.sellPrice")
    @Mapping(target = "amountStock", source = "shoppingCartProduct.stock.amount")
    ShoppingCartProductDto shoppingCartProductToShoppingCartProductDto(
            ShoppingCartProduct shoppingCartProduct);
}
