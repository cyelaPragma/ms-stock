package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.exceptions.ProductNotFoundException;
import com.acelera.ti.stock.domain.model.gateways.repositories.ShoppingCartRepository;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCartProduct;
import com.acelera.ti.stock.domain.usecase.GetShoppingCartUseCase;
import com.acelera.ti.stock.mock.cart.ShoppingCartMocks;
import com.acelera.ti.stock.mock.cart.ShoppingCartProductMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DeleteProductsByCartUseCaseTest {
    @InjectMocks
    private DeleteProductsByCartUseCase deleteProductsByCartUseCase;

    @Mock
    private GetShoppingCartUseCase getShoppingCartUseCase;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @BeforeEach
    void setUp() {
        deleteProductsByCartUseCase = new DeleteProductsByCartUseCase(getShoppingCartUseCase, shoppingCartRepository);
    }

    @Test
    @DisplayName("Given a shopping cart for a valid user, " +
            "when I delete a product by its ID, then it should be removed from the product list. ")
    void testDeleteProductFromCart() {
        // Configurar
        long userId = 1L;
        long productId = 5L;
        ShoppingCart expectedCart = ShoppingCartMocks.getShoppingCart(userId);
        ShoppingCartProduct productToDelete = ShoppingCartProductMocks.getShoppingCartProduct(productId);
        when(getShoppingCartUseCase.action(userId)).thenReturn(expectedCart);

        // Ejecutar
        deleteProductsByCartUseCase.action(productId, userId);

        // Verificar
        assertEquals(4, expectedCart.getProducts().size());
        assertEquals(expectedCart.getLastUpdate(), LocalDate.now());
        assertFalse(expectedCart.getProducts().contains(productToDelete));
    }

    @Test
    @DisplayName("Given a non-existent product, When trying to delete it from the shopping cart, " +
            "Then the cart should remain the same")
    void testDeleteNonexistentProductFromCart() {
        // Configurar
        long userId = 1L;
        long productId = 10L; // un ID de producto que no está en el carrito
        ShoppingCart expectedCart = ShoppingCartMocks.getShoppingCart(userId);
        int initialSize = expectedCart.getProducts().size();
        LocalDate initialLastUpdate = expectedCart.getLastUpdate();
        when(getShoppingCartUseCase.action(userId)).thenReturn(expectedCart);

        // Ejecutar
        deleteProductsByCartUseCase.action(productId, userId);

        // Verificar
        assertEquals(initialSize, expectedCart.getProducts().size());
        assertEquals(initialLastUpdate, expectedCart.getLastUpdate());
    }

    @Test
    @DisplayName("Given an empty cart, When trying to delete a product, " +
            "Then a ProductNotFoundException should be thrown")
    void testDeleteProductFromEmptyCart() {
        // Configurar
        long userId = 2L;
        long productId = 5L;
        ShoppingCart emptyCart = ShoppingCart.builder().build();

        // Ejecutar
        when(getShoppingCartUseCase.action(userId)).thenReturn(emptyCart);

        // verificar
        assertThrows(ProductNotFoundException.class, () -> deleteProductsByCartUseCase.action(productId, userId));
        verify(getShoppingCartUseCase).action(userId);
    }
}


