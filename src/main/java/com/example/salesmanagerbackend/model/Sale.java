package com.example.salesmanagerbackend.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date saleDate;

    @Column(nullable = false)
    private Double total;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SaleProduct> saleProducts = new HashSet<>();

    public Sale() {}

    public Sale(Date saleDate, Double total) {
        this.saleDate = saleDate;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Set<SaleProduct> getSaleProducts() {
        return saleProducts;
    }
    public void addSaleProduct(SaleProduct saleProduct) {
        saleProducts.add(saleProduct);
        saleProduct.setSale(this);
    }

    public void setSaleProducts(Set<SaleProduct> saleProducts) {
        this.saleProducts = saleProducts;
    }
}
