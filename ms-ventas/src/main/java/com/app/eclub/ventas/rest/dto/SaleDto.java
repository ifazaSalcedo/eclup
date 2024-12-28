package com.app.eclub.ventas.rest.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleDto implements Serializable{
    private Long idSale;
    private LocalDateTime date= LocalDateTime.now();
    @NotEmpty(message = "The details list cannot be empty")
    private List<SaleDetailDto> details= new ArrayList<>();
}
