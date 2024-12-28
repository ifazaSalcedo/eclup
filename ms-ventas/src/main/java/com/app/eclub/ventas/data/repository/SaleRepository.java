package com.app.eclub.ventas.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.eclub.ventas.data.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{

    

}
