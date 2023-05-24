package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.exceptions.InsufficientStockException;
import com.acelera.ti.stock.domain.model.exceptions.NotExistStocksException;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCartProduct;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.domain.usecase.GetShoppingCartUseCase;
import com.acelera.ti.stock.domain.usecase.GetStockUseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AddProductToCart {
    private final GetShoppingCartUseCase getShoppingCartUseCase;
    private final GetStockUseCase getStockUseCase;

    public ShoppingCart action(Long userId, Long productId, int amount) {
        ShoppingCart shoppingCart = getShoppingCartUseCase.action(userId);
        Stock stock = getStockUseCase.action(productId);
        checkStockAvailability(stock, amount);
        Optional<ShoppingCartProduct> existingProduct = getProduct(productId, shoppingCart);
        if (existingProduct.isPresent()) {
            updateProductAmount(existingProduct.get(), amount);
        } else {
            addNewProduct(shoppingCart, productId, amount);
        }
        return shoppingCart;
    }

    private void checkStockAvailability(Stock stock, int amount) throws InsufficientStockException {
        if (stock.getAmount() < amount) {
            throw new InsufficientStockException(stock.getId(), stock.getAmount());
        }
    }

    private Optional<ShoppingCartProduct> getProduct(Long productId, ShoppingCart shoppingCart) {
        return shoppingCart.getProducts()
                .stream()
                .filter(product -> product.getStock().getId().equals(productId))
                .findFirst();
    }

    private void updateProductAmount(ShoppingCartProduct product, int amount) {
        int newAmount = product.getAmount() + amount;
        product.setAmount(newAmount);
    }

    private void addNewProduct(ShoppingCart shoppingCart, Long productId, int amount) {
        ShoppingCartProduct newProduct = ShoppingCartProduct.builder()
                .stock(getStockUseCase.action(productId))
                .amount(amount)
                .build();
        shoppingCart.getProducts().add(newProduct);
    }
}
