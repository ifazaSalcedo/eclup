package com.app.eclub.ventas.data.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

import com.app.eclub.ventas.rest.dto.ProductDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long idSale;
    @Column(name = "sale_date")
    private LocalDateTime date;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleDetail> details= new ArrayList<>();
    
    public void addDetail(SaleDetail saleDetail){
        saleDetail.setSale(this);
        details.add(saleDetail);
    }

    public void removeDetail(SaleDetail saleDetail){
        details.remove(saleDetail);
    }

}
