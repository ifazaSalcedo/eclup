package com.app.eclub.stock.rest.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.eclub.stock.data.service.ProductService;
import com.app.eclub.stock.rest.dto.ProductDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stock")
public class ProductController {

        private final ProductService service;

        @PostMapping
        public ResponseEntity<?> addStock(@Valid @RequestBody ProductDto productoDto){
            ProductDto productDtoSave= service.save(productoDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(productDtoSave);
        }

        @GetMapping("/{productId}")
        public ResponseEntity<?> findStokById(@PathVariable String productId){
            ProductDto productDtoSave= service.findById(productId);
            return ResponseEntity.ok(productDtoSave);
        }

        @GetMapping
        public ResponseEntity<?> findAll(@RequestParam int page, @RequestParam int size){
            Page<ProductDto> pageList= service.findAll(PageRequest.of(page, size));
            return ResponseEntity.ok(pageList);
        }
        
        @GetMapping("/list-product")
        public ResponseEntity<?> findAllById(@RequestParam List<String> list){
            return ResponseEntity.ok(service.findAllProductById(list));
        }

}
