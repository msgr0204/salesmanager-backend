package com.example.salesmanagerbackend.model;

import jakarta.persistence.*;

@Entity
public class SaleProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    public SaleProduct() {}

    public SaleProduct(Sale sale, Product product, int quantity) {
        this.sale = sale;
        this.product = product;
        this.quantity = quantity;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
        if (sale != null) {
            sale.getSaleProducts().add(this);
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
