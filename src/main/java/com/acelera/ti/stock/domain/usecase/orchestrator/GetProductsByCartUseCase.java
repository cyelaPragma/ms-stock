package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCartProduct;
import com.acelera.ti.stock.domain.usecase.GetPageShoppingCartProductUseCase;
import com.acelera.ti.stock.domain.usecase.GetProductUseCase;
import com.acelera.ti.stock.domain.usecase.GetShoppingCartUseCase;
import lombok.RequiredArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class GetProductsByCartUseCase {
    private final GetShoppingCartUseCase getShoppingCartUseCase;
    private final GetPageShoppingCartProductUseCase getPageShoppingCartProductUse;
    private final GetProductUseCase getProductUseCase;

    public Set<ShoppingCartProduct> action(Long userId, int pageNumber, int pageSize){
        ShoppingCart shoppingCart = getShoppingCartUseCase.action(userId);
        return getShoppingCartProductResponse(shoppingCart, pageNumber, pageSize);
    }

    private Set<ShoppingCartProduct> getShoppingCartProductResponse(ShoppingCart shoppingCart, int pageNumber, int pageSize) {
        Set<ShoppingCartProduct> shoppingCartProducts = getPageShoppingCartProductUse.action(shoppingCart.getProducts(),pageNumber, pageSize);
        shoppingCartProducts = setProductToStockInShoppingCartProduct(shoppingCartProducts);
        return shoppingCartProducts;
    }

    private Set<ShoppingCartProduct> setProductToStockInShoppingCartProduct(Set<ShoppingCartProduct> shoppingCartProducts) {
        Set<ShoppingCartProduct> shoppingCartProductResponse = new HashSet<>();
        for(ShoppingCartProduct shoppingCartProduct: shoppingCartProducts){
            Long idProduct = shoppingCartProduct.getStock().getProduct().getId();
            shoppingCartProduct.getStock().setProduct(getProductUseCase.action(idProduct));
            shoppingCartProductResponse.add(shoppingCartProduct);
        }
        return shoppingCartProductResponse;
    }
}
