package com.example.salesmanagerbackend.repository;

import com.example.salesmanagerbackend.model.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleProductRepository extends JpaRepository<SaleProduct, Long> {
}
