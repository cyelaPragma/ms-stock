package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.NotExistShoppingCartProductPageException;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCartProduct;
import com.acelera.ti.stock.mock.cart.ShoppingCartProductMocks;
import jdk.jfr.Name;
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
    @Name("Test to check if the correct page is returned")
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
    @Name("Verify that the default page size is 20")
    void getDefaultPageSizeTest(){
        Set<ShoppingCartProduct> shoppingCartProducts = ShoppingCartProductMocks.getProducts(20);
        int pageNumber = 0;
        int pageSize = 0;
        Set<ShoppingCartProduct> shoppingCartProductsResponse = getPageShoppingCartProductUseCase.action(shoppingCartProducts, pageNumber, pageSize);
        assertEquals(shoppingCartProducts.size(),shoppingCartProductsResponse.size());
    }

    @Test
    @Name("Check if NotExistShoppingCartPageException is thrown when requesting a non-existent page")
    void getNotShoppingCartPageExistTest(){
        Set<ShoppingCartProduct> shoppingCartProducts = ShoppingCartProductMocks.getProducts(25);
        int pageNumber = 10;
        int pageSize = 10;
        assertThrows(NotExistShoppingCartProductPageException.class, () -> getPageShoppingCartProductUseCase.action(
                shoppingCartProducts, pageNumber, pageSize));
    }

    @Test
    @Name("Check if NotExistShoppingCartPageException is thrown when ShoppingCartProduct list is empty")
    void getShoppingCartPageWithNullInputTest(){
        int pageNumber = 10;
        int pageSize = 10;
        assertThrows(NotExistShoppingCartProductPageException.class, () -> getPageShoppingCartProductUseCase.action(
                        null, pageNumber, pageSize));
    }
}
