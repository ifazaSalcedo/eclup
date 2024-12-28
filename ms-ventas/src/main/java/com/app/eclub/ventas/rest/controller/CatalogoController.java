package com.app.eclub.ventas.rest.controller;


import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.eclub.ventas.data.service.SaleService;
import com.app.eclub.ventas.rest.dto.ProductDto;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {

    private final SaleService service;
    
    @GetMapping("/{productId}")
    public ResponseEntity<?> findProductById(@PathVariable String productId){

        ProductDto productDto= service.getProductById(productId);

        return ResponseEntity.ok(productDto);

    }

    @GetMapping
    public ResponseEntity<?> findAllProduct(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue="50") int size){
        return ResponseEntity.ok(service.findAllProduct(page, size));
    }

}
