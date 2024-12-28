package com.app.eclub.stock.data.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.eclub.stock.data.entity.Product;
import com.app.eclub.stock.data.repository.ProductoRepository;
import com.app.eclub.stock.exception.ProductoIdExistException;
import com.app.eclub.stock.exception.ResourceNotFoundException;
import com.app.eclub.stock.exception.StockNotFountException;
import com.app.eclub.stock.rest.dto.ProductDto;
import com.app.eclub.stock.rest.dto.SaleDetailDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductoRepository repository;
    private final ProductServiceFactory factory;

    @Override
    public ProductDto save(ProductDto productDto) {
        
        if(repository.existsById(productDto.getProductId())){
            throw new ProductoIdExistException("This Cod. the product " + productDto.getProductId() +" is exist");
        }

        Product product= factory.converToEntity(productDto);

        return factory.converToDto(repository.save(product));
    }

    @Override
    public ProductDto findById(String id) {
        Product product= repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Producto", "Codigo", id));
        return factory.converToDto(product);
    }

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        Page<Product> productCatalog= repository.findAll(pageable);
        return productCatalog.map(factory::converToDto);
    }

    @Override
    public List<ProductDto> findAllProductById(Iterable<String> list) {
        List<Product> allById = repository.findAllById(list);
        return allById.stream().map(factory::converToDto).toList();
    }

    @Override
    @Transactional
    public void updateStock(List<SaleDetailDto> listProductSales) {
        
        listProductSales.stream()
        .forEach(product -> {
            Product productDb= repository.findById(product.getCodProduct())
            .orElseThrow(() -> new ResourceNotFoundException("Producto", "Codigo", product.getCodProduct()));
            productDb.setStock(productDb.getStock().subtract(product.getAmountSale()));
            if (productDb.getStock().compareTo(BigDecimal.ZERO) < 0) {
                throw new StockNotFountException("The quantity is  the product " + productDb.getCod() + " is insufficient");
            }       
            repository.save(productDb);
        });
        
        
    }

    
    

}
