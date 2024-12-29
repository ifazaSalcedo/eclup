package com.app.eclub.ventas.rest.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.eclub.ventas.data.service.SaleService;
import com.app.eclub.ventas.rest.dto.SaleDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ventas")
public class SaleController {

    private  final SaleService service;


    @PostMapping
    public ResponseEntity<?> saveSale(@Valid @RequestBody SaleDto saleDto){
            SaleDto saleDtoSave =service.save(saleDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saleDtoSave);
        
    }



}
