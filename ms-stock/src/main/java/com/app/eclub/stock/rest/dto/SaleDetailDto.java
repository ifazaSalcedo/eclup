package com.app.eclub.stock.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleDetailDto implements Serializable{
    private Long idSale;
    private String codProduct;
    private BigDecimal amountSale;
    private BigDecimal unitePrice;
    private BigDecimal totalPrice;
}
