package com.example.salesmanagerbackend.controller;

import com.example.salesmanagerbackend.service.ReportService;
import com.example.salesmanagerbackend.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import com.itextpdf.text.DocumentException;

@RestController
@CrossOrigin(origins = "http://localhost:5175")
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;


    @GetMapping("/daily/{date}")
    public ResponseEntity<Report> getDailyReport(@PathVariable String date) {
        Report report = reportService.getDailyReport(date);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping("/daily/{date}/export/csv")
    public ResponseEntity<String> exportReportToCSV(@PathVariable String date) {
        try {
            Report report = reportService.getDailyReport(date);
            reportService.exportReportToCSV(report, date);
            return new ResponseEntity<>("Report succesfully exported to CSV", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error exporting report", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/daily/{date}/export/pdf")
    public ResponseEntity<String> exportReportToPDF(@PathVariable String date) {
        try {
            Report report = reportService.getDailyReport(date);
            reportService.exportReportToPDF(report, date);
            return new ResponseEntity<>("Report succesfully exported to PDF", HttpStatus.OK);
        } catch (DocumentException | IOException e) {
            return new ResponseEntity<>("Error exporting report PDF", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
