package com.example.salesmanagerbackend.repository;

import com.example.salesmanagerbackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
