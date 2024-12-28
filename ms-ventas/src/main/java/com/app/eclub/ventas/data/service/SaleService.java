package com.app.eclub.ventas.data.service;

import org.springframework.data.domain.Page;

import com.app.eclub.ventas.rest.dto.ProductDto;
import com.app.eclub.ventas.rest.dto.SaleDto;

public interface SaleService {

    SaleDto save(SaleDto saleDto);
    
    ProductDto getProductById(String idProduct);

    Page<ProductDto> findAllProduct(int page, int size);

}
