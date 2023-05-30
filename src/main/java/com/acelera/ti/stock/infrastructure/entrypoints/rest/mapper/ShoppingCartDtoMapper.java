package com.acelera.ti.stock.infrastructure.entrypoints.rest.mapper;

import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCartProduct;
import com.acelera.ti.stock.infrastructure.entrypoints.rest.dto.ShoppingCartDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.Set;


@Mapper(componentModel = "spring")
public interface ShoppingCartDtoMapper {
/*
    @Mapping(source = "productsId", target = "products", qualifiedByName = "getProducts" )
    ShoppingCartDto ShoppingCartToDto(ShoppingCart shoppingCart);

    @Mapping(source = "productsId", target = "products", qualifiedByName = "" )
    ShoppingCart ShoppingCartDtoToDomain(ShoppingCartDto shoppingCartDto);

    @Named("getProducts")
    default Set<Long> mapShoppingCartProductEntities(Set<ShoppingCartProduct> shoppingCartProducts) {
        Set<Long> productIds = new HashSet<>();
        for (ShoppingCartProduct shoppingCartProduct : shoppingCartProducts) {
            productIds.add(shoppingCartProduct.getStock().getId());
        }
        return productIds;
    }

    @Named("getProductsDto")
    default Set<ShoppingCartProduct> mapShoppingCartProductDto(Set<Long> productsIds) {
        Set<ShoppingCartProduct> productIds = new HashSet<>();
        for (ShoppingCartProduct shoppingCartProduct : shoppingCartProducts) {
            productIds.add(shoppingCartProduct.getStock().getId());
        }
        return productIds;
    }
 */
}

