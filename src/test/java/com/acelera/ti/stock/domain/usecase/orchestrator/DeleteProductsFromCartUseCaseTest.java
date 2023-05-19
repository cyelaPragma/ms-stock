package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.gateways.repositories.ShoppingCartRepository;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.domain.usecase.GetShoppingCartUseCase;
import com.acelera.ti.stock.mock.cart.ShoppingCartMocks;
import com.acelera.ti.stock.mock.cart.ShoppingCartProductMocks;
import com.acelera.ti.stock.mock.user.PersonMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DeleteProductsFromCartUseCaseTest {
    @InjectMocks
    private DeleteProductsFromCartUseCase deleteProductsFromCartUseCase;

    @Mock
    private GetShoppingCartUseCase getShoppingCartUseCase;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteProductsFromCartUseCase = new DeleteProductsFromCartUseCase(getShoppingCartUseCase, shoppingCartRepository);
    }

    @Test
    @DisplayName("Given a shopping cart for a valid user, " +
            "when I delete a product by its ID, then it should be removed from the product list. ")
    void testDeleteProductFromCart() {
        // Configurar
        long userId = 1L;
        long productId = 2L;

        ShoppingCart expectedCart = ShoppingCart.builder()
                .id(1L)
                .user(PersonMocks.getPerson(userId))
                .products(ShoppingCartProductMocks.getProducts(2))
                .lastUpdate(LocalDate.now())
                .build();
        when(getShoppingCartUseCase.action(userId)).thenReturn(expectedCart);

        // Ejecutar
        deleteProductsFromCartUseCase.action(productId, userId);

        // Verificar
        verify(shoppingCartRepository).removeProductFromCart(productId, expectedCart.getId());
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
        deleteProductsFromCartUseCase.action(productId, userId);

        // Verificar
        assertEquals(initialSize, expectedCart.getProducts().size());
        assertEquals(initialLastUpdate, expectedCart.getLastUpdate());
    }
}



