package com.acelera.ti.stock.infrastructure.drivenadapters.productservice.feigmClient;

import com.acelera.ti.stock.domain.model.model.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "USER-MOCK-API", url = "${external.mock.api.base-url}")
public interface ProductFeignClient {

    @GetMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Product> getAllProducts();

    @GetMapping(value = "/products/{idProduct}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Product findProductById(@PathVariable("idProduct") Long idProduct);
}
