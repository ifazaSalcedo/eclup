package com.app.eclub.ventas.rest.controller;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.eclub.ventas.data.service.SaleService;
import com.app.eclub.ventas.rest.dto.SaleDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ventas")
public class SaleController {

    private  final SaleService service;
    // private  final RabbitTemplate rabbitTemplate;
    // private  final RabbitAdmin rabbitAdmin;
    // private static final String EXCHANGE_NAME = "exchage_ventas_stock";
    // private static final String QUEUE_NAME = "ventas_queue";
    // private static final String ROUTING_KEY = "ventas_queue_key";

    @PostMapping
    public ResponseEntity<?> saveSale(@Valid @RequestBody SaleDto saleDto){
            SaleDto saleDtoSave =service.save(saleDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saleDtoSave);
        
    }

    // @PostConstruct
    // protected void init(){
    //     rabbitAdmin.declareExchange(new DirectExchange(EXCHANGE_NAME, true, false));
    //     rabbitAdmin.declareQueue(new Queue(QUEUE_NAME, true)); // Cola durable
    //     rabbitAdmin.declareBinding(new Binding(QUEUE_NAME, DestinationType.QUEUE, EXCHANGE_NAME, ROUTING_KEY, null));
    // }

}
