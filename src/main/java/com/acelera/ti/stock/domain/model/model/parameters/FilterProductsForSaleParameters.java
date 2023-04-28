package com.acelera.ti.stock.domain.model.model.parameters;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
public class FilterProductsForSaleParameters {
    private List<String> categoriesName;
    private List<String> brandsName;
    private List<String> productsName;
}
