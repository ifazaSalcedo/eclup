package com.app.eclub.stock.rest.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleDto implements Serializable{
    private Long idSale;
    private LocalDateTime date;
    private List<SaleDetailDto> details= new ArrayList<>();
}
