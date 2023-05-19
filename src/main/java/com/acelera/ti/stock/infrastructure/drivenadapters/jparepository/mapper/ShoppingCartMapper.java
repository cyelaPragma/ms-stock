package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.mapper;

import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCartProduct;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.ShoppingCartEntity;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.ShoppingCartProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ShoppingCartMapper {
    @Mapping(source = "user.id", target = "idUser")
    @Mapping(source = "products", target = "products", qualifiedByName = "getProductEntities")
    ShoppingCartEntity shoppingCartToShoppingCartEntity(ShoppingCart shoppingCart);

    @Mapping(source = "idUser", target = "user.id")
    @Mapping(source = "products", target = "products", qualifiedByName = "getProducts")
    ShoppingCart shoppingCartEntityToShoppingCart(ShoppingCartEntity shoppingCartEntity);

    @Mapping(source = "stock.product.id", target = "stock.productId")
    ShoppingCartProductEntity shoppingCartProductToShoppingCartProductEntity(ShoppingCartProduct shoppingCartProduct);

    @Mapping(source = "stock.productId", target = "stock.product.id")
    ShoppingCartProduct shoppingCartProductEntityToShoppingCartProduct(ShoppingCartProductEntity shoppingCartProductEntity);

    @Named("getProductEntities")
    default Set<ShoppingCartProductEntity> mapShoppingCarProduct(Set<ShoppingCartProduct> shoppingCartProducts) {
        Set<ShoppingCartProductEntity> shoppingCartProductEntities = new HashSet<>();
        for (ShoppingCartProduct shoppingCartProduct : shoppingCartProducts) {
            shoppingCartProductEntities.add(this.shoppingCartProductToShoppingCartProductEntity(shoppingCartProduct));
        }
        return shoppingCartProductEntities;
    }

    @Named("getProducts")
    default Set<ShoppingCartProduct> mapShoppingCarProductEntity(Set<ShoppingCartProductEntity> shoppingCartProductEntities) {
        Set<ShoppingCartProduct> shoppingCartProducts = new HashSet<>();
        for (ShoppingCartProductEntity shoppingCartProduct : shoppingCartProductEntities) {
            shoppingCartProducts.add(this.shoppingCartProductEntityToShoppingCartProduct(shoppingCartProduct));
        }
        return shoppingCartProducts;
    }
}


