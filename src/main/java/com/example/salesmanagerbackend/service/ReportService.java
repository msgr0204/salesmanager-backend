package com.example.salesmanagerbackend.service;

import com.example.salesmanagerbackend.model.Sale;
import com.example.salesmanagerbackend.repository.SaleRepository;
import com.example.salesmanagerbackend.repository.SaleProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleProductRepository saleProductRepository;

    // Obtener el número de transacciones y los datos necesarios
    public Report getDailyReport(String date) {
        // Número de transacciones realizadas
        Long transactionCount = saleRepository.getSalesByDate(date).size();

        // Lista de productos vendidos
        List<Object[]> productQuantities = saleRepository.getTotalQuantityByProduct(date);

        // Total de ingresos
        Double totalRevenue = saleRepository.getTotalRevenueByDate(date);

        return new Report(transactionCount, productQuantities, totalRevenue);
    }

    // Exportar el reporte a CSV
    public void exportReportToCSV(Report report, String date) throws IOException {
        String fileName = "sales_report_" + date + ".csv";
        FileWriter fileWriter = new FileWriter(fileName);

        fileWriter.append("Transaction Count, " + report.getTransactionCount() + "\n");
        fileWriter.append("Product, Quantity Sold\n");

        for (Object[] product : report.getProductQuantities()) {
            fileWriter.append(product[0].toString() + ", " + product[1].toString() + "\n");
        }

        fileWriter.append("Total Revenue, " + report.getTotalRevenue() + "\n");
        fileWriter.flush();
        fileWriter.close();
    }
}
