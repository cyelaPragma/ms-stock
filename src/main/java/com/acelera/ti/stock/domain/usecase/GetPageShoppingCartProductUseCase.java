package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.NotExistShoppingCartProductPageException;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCartProduct;
import java.util.Set;
import java.util.stream.Collectors;

public class GetPageShoppingCartProductUseCase {
    static final int PAGE_SIZE_DEFAULT = 20;

    public Set<ShoppingCartProduct> action (Set<ShoppingCartProduct> shoppingCartProducts, int pageNumber, int pageSize) {
        if (pageSize == 0) {
            pageSize = PAGE_SIZE_DEFAULT;
        }
        if(shoppingCartProducts == null || !isPageNumberExist(pageNumber, pageSize, shoppingCartProducts.size())){
            throw new NotExistShoppingCartProductPageException();
        }
        return shoppingCartProducts.stream()
                .skip((long) pageNumber * pageSize)
                .limit(pageSize)
                .collect(Collectors.toSet());
    }

    public boolean isPageNumberExist(int pageNumber, int pageSize, int totalShoppingCartProducts) {
        int totalPages = (int) Math.ceil((double)totalShoppingCartProducts / (double)pageSize);
        return pageNumber <= totalPages;
    }
}
