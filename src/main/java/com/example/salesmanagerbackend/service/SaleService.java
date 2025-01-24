package com.example.salesmanagerbackend.service;

import com.example.salesmanagerbackend.model.Sale;
import com.example.salesmanagerbackend.model.SaleRequest;
import com.example.salesmanagerbackend.model.Product;
import com.example.salesmanagerbackend.model.SaleProduct;
import com.example.salesmanagerbackend.repository.ProductRepository;
import com.example.salesmanagerbackend.repository.SaleProductRepository;
import com.example.salesmanagerbackend.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleProductRepository saleProductRepository;

    @Autowired
    private StockService stockService;  // Inyectamos StockService para validar el stock

    public Sale createSaleWithProducts(SaleRequest saleRequest) {

        stockService.validateStock(saleRequest.getProducts());


        Sale sale = new Sale();
        sale.setSaleDate(saleRequest.getSaleDate());


        double total = 0.0;
        for (SaleRequest.ProductRequest productRequest : saleRequest.getProducts()) {
            Product product = productRepository.findById(productRequest.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

            total += product.getPrice() * productRequest.getQuantity();
        }

        sale.setTotal(total);
        Sale savedSale = saleRepository.save(sale);


        for (SaleRequest.ProductRequest productRequest : saleRequest.getProducts()) {
            Product product = productRepository.findById(productRequest.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

            SaleProduct saleProduct = new SaleProduct(savedSale, product, productRequest.getQuantity());
            saleProductRepository.save(saleProduct);
        }

        return savedSale;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }

    public Sale updateSale(Sale sale) {
        if (saleRepository.existsById(sale.getId())) {
            return saleRepository.save(sale);
        }
        throw new IllegalArgumentException("Sale not found with id: " + sale.getId());
    }

    public void deleteSale(Long id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Sale not found with id: " + id);
        }
    }
}
