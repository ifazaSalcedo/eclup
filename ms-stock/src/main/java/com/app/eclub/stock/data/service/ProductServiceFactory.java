package com.app.eclub.stock.data.service;


import org.springframework.stereotype.Component;

import com.app.eclub.stock.data.entity.Product;
import com.app.eclub.stock.rest.dto.ProductDto;

@Component
public class ProductServiceFactory {

    public Product converToEntity(ProductDto productDto) {
        return new Product(productDto);
    }

    public ProductDto converToDto(Product product) {
        return new ProductDto(product);
    }

}
