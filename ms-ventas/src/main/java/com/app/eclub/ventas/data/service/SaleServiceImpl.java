package com.app.eclub.ventas.data.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.app.eclub.ventas.configure.RabbitConfig;
import com.app.eclub.ventas.data.entity.Sale;
import com.app.eclub.ventas.data.repository.SaleRepository;
import com.app.eclub.ventas.exception.RabbitMqNotFountException;
import com.app.eclub.ventas.exception.StockNotFountException;
import com.app.eclub.ventas.rest.client.StockClient;
import com.app.eclub.ventas.rest.dto.ProductDto;
import com.app.eclub.ventas.rest.dto.SaleDetailDto;
import com.app.eclub.ventas.rest.dto.SaleDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class SaleServiceImpl implements SaleService{
    private final SaleRepository repository;
    private final SaleServiceFactory factory;
    private final StockClient stockClientRest;
    private final RabbitTemplate rabbitTemplate;

        @Override
        @Transactional
        public SaleDto save(SaleDto saleDto) {

            List<String> detailSales = saleDto.getDetails()
            .stream().map(SaleDetailDto::getCodProduct)
            .toList();

            List<ProductDto> detailProductStock = stockClientRest.findAllById(detailSales);


            // Crear un mapa para acceder rápidamente al stock disponible
            Map<String, BigDecimal> productStockMap = detailProductStock.stream()
                .collect(Collectors.toMap(ProductDto::getProductId, ProductDto::getQuantity));

            // Verificar si la cantidad de productos en la venta es mayor a la cantidad en stock
            validateStockAvailability(saleDto, productStockMap);

            Sale saleSave = repository.save(factory.converToSaleEntity(saleDto));

            SaleDto saleSaveDto= factory.converToSaleDto(saleSave);

            sendSaleToStockEchage(saleSaveDto);

            return saleSaveDto;

        }
    
    private void validateStockAvailability(SaleDto saleDto, Map<String, BigDecimal> productStockMap){
        saleDto.getDetails().forEach(saleDetail -> {
            BigDecimal availableStock = productStockMap.getOrDefault(saleDetail.getCodProduct(), BigDecimal.ZERO);
            if(saleDetail.getAmountSale().compareTo(availableStock)>0){
                throw new StockNotFountException("Insufficient stock for product. " + 
                saleDetail.getCodProduct() +
                " Requested: " + 
                saleDetail.getAmountSale() 
                + ", Available: " +
                availableStock);
            }
        });
    }
    private void sendSaleToStockEchage(SaleDto saleDtoSave){
        try {
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_STOCK_NAME, RabbitConfig.ROUTING_STOCK_KEY, saleDtoSave);
        } catch (AmqpException e) {
            throw new RabbitMqNotFountException("The operation could not be completed. The broker cannot communicate");
        }
    }

    @Override
    public ProductDto getProductById(String idProduct) {
        ProductDto productDto= stockClientRest.findStokById(idProduct);
        return productDto;
    }

    @Override
    public Page<ProductDto> findAllProduct(int page, int size) {
        return stockClientRest.findAll(page, size);
    }

    


    
}
