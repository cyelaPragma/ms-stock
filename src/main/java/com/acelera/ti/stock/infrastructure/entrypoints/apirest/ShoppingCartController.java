package com.acelera.ti.stock.infrastructure.entrypoints.apirest;

import com.acelera.ti.stock.domain.model.exceptions.ProductNotFoundException;
import com.acelera.ti.stock.domain.model.exceptions.ShoppingCartNotFoundException;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.domain.usecase.GetShoppingCartUseCase;
import com.acelera.ti.stock.domain.usecase.SaveShoppingCartUseCase;
import com.acelera.ti.stock.domain.usecase.orchestrator.DeleteProductsFromCartUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private final DeleteProductsFromCartUseCase deleteProductsFromCartUseCase;
    private final GetShoppingCartUseCase getShoppingCartUseCase;
    private final SaveShoppingCartUseCase saveShoppingCartUseCase;

    @GetMapping("/{idUserCart}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable("idUserCart") Long idUserCart) {
        ShoppingCart shoppingCart = getShoppingCartUseCase.action(idUserCart);
        HttpStatus status = shoppingCart.getId() != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(shoppingCart, status);
    }

    @PostMapping
    public ResponseEntity<ShoppingCart> saveShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCart savedShoppingCart = saveShoppingCartUseCase.action(shoppingCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShoppingCart);
    }

    @DeleteMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<Void> removeProductFromCart(
            @RequestParam("productId") Long productId, @RequestParam("userId") Long userId) {
        try {
            deleteProductsFromCartUseCase.action(productId, userId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (ProductNotFoundException | ShoppingCartNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}


