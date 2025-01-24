package com.example.salesmanagerbackend.service;

import com.example.salesmanagerbackend.exception.InsufficientStockException;
import com.example.salesmanagerbackend.model.SaleRequest;
import com.example.salesmanagerbackend.model.Product;
import com.example.salesmanagerbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private ProductRepository productRepository;  // Repositorio para obtener el producto

    public void validateStock(List<SaleRequest.ProductRequest> productRequests) {
        for (SaleRequest.ProductRequest productRequest : productRequests) {
            Product product = productRepository.findById(productRequest.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + productRequest.getProductId()));

            if (product.getStock() < productRequest.getQuantity()) {
                throw new InsufficientStockException("No hay suficiente stock para el producto: " + productRequest.getProductId());
            }
        }
    }
}
