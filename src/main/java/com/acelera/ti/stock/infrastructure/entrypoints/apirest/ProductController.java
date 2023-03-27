package com.acelera.ti.stock.infrastructure.entrypoints.apirest;

import com.acelera.ti.stock.domain.model.model.product.Product;
import com.acelera.ti.stock.domain.usecase.GetAllProductsUseCase;
import com.acelera.ti.stock.domain.usecase.GetProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final GetProductUseCase getProductUseCase;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = getAllProductsUseCase.action();
        HttpStatus status = products.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(products, status);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<Product> findProductById(@PathVariable("idProduct") Long idProduct) {
        Product product = getProductUseCase.findProductById(idProduct);
        HttpStatus status = product.getId() != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(product, status);
    }
}
