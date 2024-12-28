package com.app.eclub.ventas.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleDetailDto implements Serializable{
    @NotNull(message = "Sale Id is required")
    private Long idSale;
    @NotBlank(message = "Product Id is required")
    private String codProduct;
    @NotNull(message = "Sales amount is required")
    @Min(value = 1, message = "Sales amount is required")
    private BigDecimal amountSale;
    private BigDecimal unitePrice;
    private BigDecimal totalPrice;
}
