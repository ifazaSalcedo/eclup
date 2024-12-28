package com.app.eclub.stock.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.eclub.stock.data.entity.Product;

public interface ProductoRepository extends JpaRepository<Product, String>{

}
