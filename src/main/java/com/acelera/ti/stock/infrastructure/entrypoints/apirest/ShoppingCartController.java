package com.acelera.ti.stock.infrastructure.entrypoints.apirest;

import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.domain.usecase.GetShoppingCartUseCase;
import com.acelera.ti.stock.domain.usecase.orchestrator.DeleteProductsByCartUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final DeleteProductsByCartUseCase deleteProductsByCartUseCase;
    private final GetShoppingCartUseCase getShoppingCartUseCase;

    @GetMapping("/{idUserCart}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable("idUserCart") Long idUserCart) {
        ShoppingCart shoppingCart = getShoppingCartUseCase.action(idUserCart);
        HttpStatus status = shoppingCart.getId() != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(shoppingCart, status);
    }

    @DeleteMapping("/{productId}/{userId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long productId, @PathVariable Long userId) {
        deleteProductsByCartUseCase.action(productId, userId);
        return ResponseEntity.noContent().build();
    }
}

