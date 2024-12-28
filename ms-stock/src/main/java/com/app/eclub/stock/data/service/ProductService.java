package com.app.eclub.stock.data.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.eclub.stock.rest.dto.ProductDto;
import com.app.eclub.stock.rest.dto.SaleDetailDto;

public interface ProductService {
    ProductDto save(ProductDto productDto);
    ProductDto findById(String id);
    Page<ProductDto> findAll(Pageable pageable);
    List<ProductDto> findAllProductById(Iterable<String> list);
    void updateStock(List<SaleDetailDto> listProductSales);
}
