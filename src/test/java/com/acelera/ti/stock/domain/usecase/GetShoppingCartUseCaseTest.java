package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.ShoppingCartNotFoundException;
import com.acelera.ti.stock.domain.model.exceptions.TechnicalException;
import com.acelera.ti.stock.domain.model.gateways.repositories.ShoppingCartRepository;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.mock.cart.ShoppingCartMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetShoppingCartUseCaseTest {
    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    private GetShoppingCartUseCase getShoppingCartUseCase;

    @BeforeEach
    void setUp() {
        getShoppingCartUseCase = new GetShoppingCartUseCase(shoppingCartRepository);
    }

    @Test
    @DisplayName("Given a valid user ID, when getShoppingCartByUserId is called, " +
            "then it should return the corresponding shopping cart")
    void testGetShoppingCartByUserId() {
        // Configurar
        long userId = 1L;
        ShoppingCart expectedCart = ShoppingCartMocks.getShoppingCart(userId);
        when(shoppingCartRepository.getShoppingCartByUserId(userId)).thenReturn(expectedCart);

        // Ejecutar
        ShoppingCart actualCart = getShoppingCartUseCase.action(userId);

        // Verificar
        assertEquals(expectedCart.getId(), actualCart.getId());
        assertEquals(expectedCart.getProducts().size(), actualCart.getProducts().size());
    }

    @Test
    @DisplayName("Given a valid user ID, when getShoppingCartByUserId is called," +
            "and ShoppingCart is null, then it should throw a ShoppingCartNotFoundException")
    void testGetShoppingCartByUserIdNotFoundException() {
        // Configurar
        long userId = 1L;
        String expectedMessage = "No existe el carrito de compras consultado";
        when(shoppingCartRepository.getShoppingCartByUserId(userId)).thenReturn(null);

        // Verificar
        assertThrows(ShoppingCartNotFoundException.class, () -> getShoppingCartUseCase.action(userId));
        ShoppingCartNotFoundException exception = assertThrows(
                ShoppingCartNotFoundException.class,
                () -> getShoppingCartUseCase.action(userId),
                "Se esperaba que lanzara una excepción de tipo ShoppingCartNotFoundException"
        );
        assertEquals(expectedMessage, exception.getMessage(),
                "Se esperaba que el mensaje de excepción fuera: " + expectedMessage);

    }

    @Test
    @DisplayName("Given a technical error when getShoppingCartByUserId is called, " +
            "then it should throw a TechnicalException")
    void readStockByIdTechnicalError() {
        // Configurar
        long userId = 1L;
        doThrow(TechnicalException.class).when(shoppingCartRepository).getShoppingCartByUserId(userId);
        // Verificar
        assertThrows(TechnicalException.class, () -> getShoppingCartUseCase.action(userId));
    }

}
