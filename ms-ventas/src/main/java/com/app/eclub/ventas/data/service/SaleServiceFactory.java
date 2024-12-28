package com.app.eclub.ventas.data.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.app.eclub.ventas.data.entity.Sale;
import com.app.eclub.ventas.data.entity.SaleDetail;
import com.app.eclub.ventas.data.entity.SaleDetailPk;
import com.app.eclub.ventas.rest.dto.SaleDetailDto;
import com.app.eclub.ventas.rest.dto.SaleDto;

@Component
public class SaleServiceFactory {

    public Sale converToSaleEntity(SaleDto saleDto) {
        Sale sale= new Sale();
        sale.setIdSale(saleDto.getIdSale());
        sale.setDate(saleDto.getDate());

        saleDto.getDetails().stream()
        .map(this::converToSaleDetail)
        .forEach(sale::addDetail);

        return sale;
    }

    public SaleDto converToSaleDto(Sale sale) {
        SaleDto saleDto= new SaleDto();
        saleDto.setIdSale(sale.getIdSale());
        saleDto.setDate(sale.getDate());
        
        saleDto.setDetails(sale.getDetails()
        .stream()
        .map(
            detail -> converToSaleDetailDto(detail))
            .collect(Collectors.toList()));

        return saleDto;
    }

    public SaleDetail converToSaleDetail(SaleDetailDto detailDto){
        
        SaleDetailPk pk= new SaleDetailPk();
        pk.setCodProduct(detailDto.getCodProduct());

        SaleDetail detailSale= new SaleDetail();
        detailSale.setPkSaleDetail(pk);
        detailSale.setAmountSale(detailDto.getAmountSale());
        detailSale.setUnitePrice(detailDto.getUnitePrice());    
        detailSale.setTotalPrice(detailDto.getTotalPrice());

        return detailSale;

    }

    public SaleDetailDto converToSaleDetailDto(SaleDetail saleDetail){
        
        SaleDetailDto saleDetailDto= new SaleDetailDto();
        saleDetailDto.setIdSale(saleDetail.getPkSaleDetail().getIdSale());
        saleDetailDto.setCodProduct(saleDetail.getPkSaleDetail().getCodProduct());
        saleDetailDto.setAmountSale(saleDetail.getAmountSale());
        saleDetailDto.setUnitePrice(saleDetail.getUnitePrice());    
        saleDetailDto.setTotalPrice(saleDetail.getTotalPrice());

        return saleDetailDto;

    }

}
