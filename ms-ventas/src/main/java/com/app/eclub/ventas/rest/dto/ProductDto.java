package com.app.eclub.ventas.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto implements Serializable{
    private String productId;
    private String productName;
    private BigDecimal productPriceSale;
    private BigDecimal quantity;

}
