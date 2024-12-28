package com.app.eclub.stock.rest.dto;

import java.math.BigDecimal;
import com.app.eclub.stock.data.entity.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    @NotBlank(message = "Product id is required")
    private String productId;
    @NotBlank(message = "Product descrip is required")
    private String productName;
    @Min(value = 1, message = "Product price is required")
    @NotNull(message = "Product price is required")
    private BigDecimal productPriceSale;
    @Min(value = 1, message = "Product quantity is required")
    @NotNull(message = "Product quantity is required")
    private BigDecimal quantity;

    public ProductDto(Product product) {
        this.productId= product.getCod();
        this.productName= product.getName();
        this.productPriceSale= product.getPriceSale();
        this.quantity= product.getStock();
    }


}
