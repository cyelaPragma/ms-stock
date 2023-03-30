package com.acelera.ti.stock.infrastructure.drivenadapters.productservice.mapper;

import com.acelera.ti.stock.domain.model.model.product.Product;
import com.acelera.ti.stock.infrastructure.drivenadapters.productservice.dto.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO productToProductDTO(Product product);
    Product productDTOToProduct(ProductDTO productDTO);
}
