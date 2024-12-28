package com.app.eclub.ventas.data.service;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.app.eclub.ventas.configure.RabbitConfig;
import com.app.eclub.ventas.data.entity.Sale;
import com.app.eclub.ventas.data.repository.SaleRepository;
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

            detailProductStock.forEach(productDto ->{
                saleDto.getDetails().stream()
                .filter(detail -> detail.getCodProduct().equals(productDto.getProductId()))
                .findFirst()
                .ifPresent(saleDetail -> {
                    if(saleDetail.getAmountSale().compareTo(productDto.getQuantity())>0){
                        throw new StockNotFountException("The quantity is  the product " + saleDetail.getCodProduct() + " is insufficient");
                    }
                });
            });


            Sale saleSave = repository.save(factory.converToSaleEntity(saleDto));

            SaleDto saleSaveDto= factory.converToSaleDto(saleSave);

            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_STOCK_NAME, RabbitConfig.ROUTING_STOCK_KEY, saleSaveDto);

            return saleSaveDto;

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
