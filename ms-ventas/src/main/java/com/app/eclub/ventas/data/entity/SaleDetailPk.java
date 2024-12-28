package com.app.eclub.ventas.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class SaleDetailPk {
    @Column(name = "sale_id")
    private Long idSale;
    @Column(name = "prod_id", length = 50)
    private String codProduct;
}
