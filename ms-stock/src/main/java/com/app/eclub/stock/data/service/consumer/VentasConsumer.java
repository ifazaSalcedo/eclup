package com.app.eclub.stock.data.service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.app.eclub.stock.config.RabbitConfig;
import com.app.eclub.stock.data.service.ProductService;
import com.app.eclub.stock.rest.dto.SaleDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentasConsumer {

    private final ProductService service;

    @RabbitListener(queues = RabbitConfig.QUEUE_STOCK_NAME)
    public void consumeSales(SaleDto saleDto){
        System.out.println("Mensaje recibido en ms-stock: " + saleDto);
        service.updateStock(saleDto.getDetails());
    }

}
