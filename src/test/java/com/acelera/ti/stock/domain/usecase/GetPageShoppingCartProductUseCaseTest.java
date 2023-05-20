package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.NotExistShoppingCartProductPageException;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCartProduct;
import com.acelera.ti.stock.mock.cart.ShoppingCartProductMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetPageShoppingCartProductUseCaseTest {
    private GetPageShoppingCartProductUseCase getPageShoppingCartProductUseCase;

    @BeforeEach
    void setUp() {
        getPageShoppingCartProductUseCase = new GetPageShoppingCartProductUseCase();
    }

    @Test
    void getPageShoppingCartProductTest() {
        Set<ShoppingCartProduct> shoppingCartProducts = ShoppingCartProductMocks.getProducts(5);
        int pageNumber = 1;
        int pageSize = 3;
        Set<ShoppingCartProduct> shoppingCartProductsResponse = getPageShoppingCartProductUseCase.action(shoppingCartProducts, pageNumber, pageSize);
        assertArrayEquals(shoppingCartProducts.stream()
                .skip((long) pageNumber * pageSize)
                .limit(pageSize).distinct().toArray(), shoppingCartProductsResponse.toArray());
    }

    @Test
    void getDefaultPageSizeTest(){
        Set<ShoppingCartProduct> shoppingCartProducts = ShoppingCartProductMocks.getProducts(20);
        int pageNumber = 0;
        int pageSize = 0;
        Set<ShoppingCartProduct> shoppingCartProductsResponse = getPageShoppingCartProductUseCase.action(shoppingCartProducts, pageNumber, pageSize);
        assertEquals(shoppingCartProducts.size(),shoppingCartProductsResponse.size());
    }

    @Test
    void getNotShoppingCartPageExistTest(){
        Set<ShoppingCartProduct> shoppingCartProducts = ShoppingCartProductMocks.getProducts(25);
        int pageNumber = 10;
        int pageSize = 10;
        assertThrows(NotExistShoppingCartProductPageException.class, () -> getPageShoppingCartProductUseCase.action(
                shoppingCartProducts, pageNumber, pageSize));
    }

    @Test
    void getShoppingCartPageWithNullInputTest(){
        int pageNumber = 10;
        int pageSize = 10;
        assertThrows(NotExistShoppingCartProductPageException.class, () -> getPageShoppingCartProductUseCase.action(
                        null, pageNumber, pageSize));
    }

    @Test
    void isPageNumberExistWhenIsFalse(){
        int pageNumber = 5;
        int pageSize = 3;
        int totalShoppingCartProducts = 2;
        assertFalse(getPageShoppingCartProductUseCase.isPageNumberExist(pageNumber,pageSize, totalShoppingCartProducts));
    }

    @Test
    void isPageNumberExistWhenIsTrue(){
        int pageNumber = 5;
        int pageSize = 3;
        int totalShoppingCartProducts = 20;
        assertTrue(getPageShoppingCartProductUseCase.isPageNumberExist(pageNumber,pageSize, totalShoppingCartProducts));
    }
}
