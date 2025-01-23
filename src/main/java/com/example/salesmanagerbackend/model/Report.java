package com.example.salesmanagerbackend.model;

import java.util.List;

public class Report {

    private int transactionCount;
    private List<Object[]> productQuantities;
    private Double totalRevenue;

    public Report(int transactionCount, List<Object[]> productQuantities, Double totalRevenue) {
        this.transactionCount = transactionCount;
        this.productQuantities = productQuantities;
        this.totalRevenue = totalRevenue;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    public List<Object[]> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(List<Object[]> productQuantities) {
        this.productQuantities = productQuantities;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
