package com.app.eclub.stock.data.entity;

import java.math.BigDecimal;

import com.app.eclub.stock.rest.dto.ProductDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "prud_id")
    private String cod;
    @Column(name = "prod_name", length = 100)
    private String name;
    @Column(name = "prod_price_sale", precision = 10, scale = 2)
    private BigDecimal priceSale;
    @Column(name = "prod_stock", precision = 10, scale = 3)
    private BigDecimal stock;


    public Product(ProductDto productDto) {

        this.cod= productDto.getProductId();
        this.name= productDto.getProductName();
        this.priceSale= productDto.getProductPriceSale();
        this.stock= productDto.getQuantity();

    }


    
}
