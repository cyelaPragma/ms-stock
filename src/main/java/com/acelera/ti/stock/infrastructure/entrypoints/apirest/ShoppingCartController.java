package com.acelera.ti.stock.infrastructure.entrypoints.apirest;

import com.acelera.ti.stock.domain.usecase.orchestrator.DeleteProductsByCartUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final DeleteProductsByCartUseCase deleteProductsByCartUseCase;

    @DeleteMapping("/{productId}/{userId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long productId, @PathVariable Long userId) {
        if (productId <= 0 || userId <= 0) {
            throw new IllegalArgumentException("productId y userId deben ser números enteros positivos");
        }
        deleteProductsByCartUseCase.action(productId, userId);
        return ResponseEntity.noContent().build();
    }

}
