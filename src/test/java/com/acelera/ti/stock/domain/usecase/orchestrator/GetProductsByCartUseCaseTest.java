package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCartProduct;
import com.acelera.ti.stock.domain.usecase.GetPageShoppingCartProductUseCase;
import com.acelera.ti.stock.domain.usecase.GetProductUseCase;
import com.acelera.ti.stock.domain.usecase.GetShoppingCartUseCase;
import com.acelera.ti.stock.mock.cart.ShoppingCartMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetProductsByCartUseCaseTest {
    @InjectMocks
    private GetProductsByCartUseCase getProductsByCartUseCase;
    @Mock
    private GetShoppingCartUseCase getShoppingCartUseCase;
    @Mock
    private GetPageShoppingCartProductUseCase getPageShoppingCartProductUseCase;
    @Mock
    private GetProductUseCase getProductUseCase;

    @BeforeEach
    void setUp() {
        getProductsByCartUseCase = new GetProductsByCartUseCase(getShoppingCartUseCase, getPageShoppingCartProductUseCase,
                                                                getProductUseCase);
    }

    @Test
    void getProductsByCartTest(){
        ShoppingCart shoppingCart = ShoppingCartMocks.getShoppingCart(1L);
        when(getShoppingCartUseCase.action(1L)).thenReturn(shoppingCart);
        when(getPageShoppingCartProductUseCase.action(shoppingCart.getProducts(),0,5)).thenReturn(shoppingCart.getProducts());
        Set<ShoppingCartProduct> shoppingCartProducts = getProductsByCartUseCase.action(1L,0,5);
        assertEquals(shoppingCart.getProducts().toArray().length, shoppingCartProducts.toArray().length);
    }
}
