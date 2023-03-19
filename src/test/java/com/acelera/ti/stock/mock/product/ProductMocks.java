package com.acelera.ti.stock.mock.product;

import com.acelera.ti.stock.domain.model.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductMocks {
    public static Product getProduct(Long id){
        return Product.builder()
                .id(id)
                .name("Producto "+id)
                .description("descripcion "+id)
                .model("modelo "+id)
                .brand(BrandMocks.getBrand(id))
                .category(CategoryMocks.getCategory(id))
                .build();
    }
    public static List<Product> getProducts(int size) {
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            products.add(getProduct((long) i));
        }
        return products;
    }
}
