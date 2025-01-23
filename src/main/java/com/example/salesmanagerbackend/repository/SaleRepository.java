package com.example.salesmanagerbackend.repository;

import com.example.salesmanagerbackend.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM Sale s WHERE DATE(s.saleDate) = DATE(:date)")
    List<Sale> getSalesByDate(String date);

    @Query("SELECT sp.product.name, SUM(sp.quantity) FROM SaleProduct sp WHERE DATE(sp.sale.saleDate) = DATE(:date) GROUP BY sp.product.name")
    List<Object[]> getTotalQuantityByProduct(String date);

    @Query("SELECT SUM(s.total) FROM Sale s WHERE DATE(s.saleDate) = DATE(:date)")
    Double getTotalRevenueByDate(String date);
}
