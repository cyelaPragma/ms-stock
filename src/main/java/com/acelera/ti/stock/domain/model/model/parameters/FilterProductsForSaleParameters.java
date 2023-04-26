package com.acelera.ti.stock.domain.model.model.parameters;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class FilterProductsForSaleParameters {
    private List<String> categoryName;
    private List<String> brandName;
    private List<String> productName;
}
