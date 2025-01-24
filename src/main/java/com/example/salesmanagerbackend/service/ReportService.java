package com.example.salesmanagerbackend.service;

import com.example.salesmanagerbackend.model.Report;
import com.example.salesmanagerbackend.repository.SaleRepository;
import com.example.salesmanagerbackend.repository.SaleProductRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class ReportService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleProductRepository saleProductRepository;

    public Report getDailyReport(String date) {
        int transactionCount = saleRepository.getSalesByDate(date).size();
        List<Object[]> productQuantities = saleRepository.getTotalQuantityByProduct(date);
        Double totalRevenue = saleRepository.getTotalRevenueByDate(date);

        return new Report(transactionCount, productQuantities, totalRevenue);
    }


    public void exportReportToCSV(Report report, String date) throws IOException {
        String reportsFolder = "reports";

        Path folderPath = Paths.get(reportsFolder);
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        String fileName = reportsFolder + File.separator + "sales_report_" + date + ".csv";

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

    public void exportReportToPDF(Report report, String date) throws DocumentException, IOException {
        String reportsFolder = "reports";

        Path folderPath = Paths.get(reportsFolder);
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        String fileName = reportsFolder + File.separator + "sales_report_" + date + ".pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));

        document.open();


        document.add(new Paragraph("Reporte de Ventas Diario"));
        document.add(new Paragraph("Fecha: " + date));
        document.add(Chunk.NEWLINE);


        document.add(new Paragraph("Número de transacciones realizadas: " + report.getTransactionCount()));
        document.add(Chunk.NEWLINE);


        document.add(new Paragraph("Productos vendidos:"));
        for (Object[] product : report.getProductQuantities()) {
            document.add(new Paragraph("Producto: " + product[0].toString() +
                    " | Cantidad: " + product[1].toString()));
        }
        document.add(Chunk.NEWLINE);


        document.add(new Paragraph("Total general de ingresos: " + report.getTotalRevenue()));
        document.close();
    }
}
