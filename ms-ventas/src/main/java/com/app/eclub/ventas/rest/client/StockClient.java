package com.app.eclub.ventas.rest.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.eclub.ventas.rest.dto.ProductDto;

@FeignClient(name = "ms-stock", url = "http://ms-stock:8080/api/stock")
public interface StockClient {
    @GetMapping("/{productId}")
    ProductDto findStokById(@PathVariable String productId);
    
    @GetMapping
    Page<ProductDto> findAll(@RequestParam int page, @RequestParam int size);
    
    @GetMapping("/list-product")
    List<ProductDto> findAllById(@RequestParam List<String> list);
}
