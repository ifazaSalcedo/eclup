package com.app.eclub.ventas.data.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "sales_details")
public class SaleDetail {
    @EmbeddedId
    private SaleDetailPk pkSaleDetail;
    @Column(name = "sale_det_amount", scale = 3, precision = 6)
    private BigDecimal amountSale;
    @Column(name = "sale_det_uniprice", scale = 2, precision = 12)
    private BigDecimal unitePrice;
    @Column(name = "sale_det_totalprice", scale = 3, precision = 12)
    private BigDecimal totalPrice;
    @ManyToOne
    @JoinColumn(name = "sale_id", updatable = false, insertable = false)
    @MapsId("idSale")
    private Sale sale;
}
