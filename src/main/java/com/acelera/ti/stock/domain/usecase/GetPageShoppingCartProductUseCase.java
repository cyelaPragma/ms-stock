package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.NotExistShoppingCartProductPageException;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCartProduct;
import java.util.Set;
import java.util.stream.Collectors;

public class GetPageShoppingCartProductUseCase {
    public Set<ShoppingCartProduct> action (Set<ShoppingCartProduct> shoppingCartProducts, int pageNumber, int pageSize) {
        if (pageSize == 0) {
            pageSize = 20;
        }
        if(shoppingCartProducts == null || !isPageNumberExist(pageNumber, pageSize, shoppingCartProducts.size())){
            throw new NotExistShoppingCartProductPageException();
        }
        return shoppingCartProducts.stream()
                .skip((long) pageNumber * pageSize)
                .limit(pageSize)
                .collect(Collectors.toSet());
    }

    private static boolean isPageNumberExist(int pageNumber, int pageSize, int totalStocks) {
        int totalPages = totalStocks / pageSize;
        if (totalStocks % pageSize != 0) {
            totalPages++;
        }
        return pageNumber <= totalPages;
    }
}
