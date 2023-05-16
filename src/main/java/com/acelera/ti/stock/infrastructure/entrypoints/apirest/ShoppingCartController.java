package com.acelera.ti.stock.infrastructure.entrypoints.apirest;

import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCartProduct;
import com.acelera.ti.stock.domain.usecase.GetShoppingCartUseCase;
import com.acelera.ti.stock.domain.usecase.orchestrator.DeleteProductsByCartUseCase;
import com.acelera.ti.stock.domain.usecase.orchestrator.GetProductsByCartUseCase;
import com.acelera.ti.stock.infrastructure.entrypoints.rest.dto.ShoppingCartProductDto;
import com.acelera.ti.stock.infrastructure.entrypoints.rest.mapper.ShoppingCartProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private final DeleteProductsByCartUseCase deleteProductsByCartUseCase;
    private final GetShoppingCartUseCase getShoppingCartUseCase;
    private final GetProductsByCartUseCase getProductsByCartUseCase;
    private final ShoppingCartProductMapper shoppingCartProductMapper;

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

    @GetMapping("/products/{userId}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Set<ShoppingCartProductDto>> getShoppingCartProducts(
            @PathVariable Long userId, @RequestParam int page, @RequestParam int size){
        Set<ShoppingCartProduct> shoppingCartProducts = getProductsByCartUseCase.action(userId,page,size);
        Set<ShoppingCartProductDto> shoppingCartProductDto = shoppingCartProductMapper.
                shoppingCartProductToShoppingCartProductDto(shoppingCartProducts);
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCartProductDto);
    }
}
