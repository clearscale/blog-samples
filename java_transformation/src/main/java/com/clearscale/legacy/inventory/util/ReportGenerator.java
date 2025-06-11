package com.clearscale.legacy.inventory.util;

import com.clearscale.legacy.inventory.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReportGenerator {
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ObjectMapper objectMapper = new ObjectMapper();

    // Legacy date formatting approach
    public String formatDate(Date date) {
        if (date == null) {
            return "N/A";
        }
        return DATE_FORMAT.format(date);
    }

    // Legacy JSON generation
    public String generateJsonReport(List<Product> products) {
        try {
            StringWriter writer = new StringWriter();
            objectMapper.writeValue(writer, products);
            return writer.toString();
        } catch (Exception e) {
            // Legacy error handling
            return String.format("Error generating JSON report: %s", e.getMessage());
        }
    }

    // Legacy CSV generation with String.format
    public String generateCsvReport(List<Product> products) {
        StringBuilder csv = new StringBuilder();
        csv.append("ID,Name,Category,Price,Quantity,Created Date,Last Updated\n");
        
        for (Product product : products) {
            String line = String.format("%d,%s,%s,%.2f,%d,%s,%s",
                    product.getId(),
                    escapeCsvField(product.getName()),
                    escapeCsvField(product.getCategory()),
                    product.getPrice(),
                    product.getQuantity(),
                    formatDate(product.getCreatedDate()),
                    formatDate(product.getLastUpdated()));
            csv.append(line).append("\n");
        }
        
        return csv.toString();
    }

    // Legacy string manipulation
    private String escapeCsvField(String field) {
        if (field == null) {
            return "";
        }
        if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }

    // Legacy summary generation
    public String generateSummaryReport(List<Product> products, Map<String, BigDecimal> categoryTotals) {
        StringBuilder summary = new StringBuilder();
        
        summary.append("=== INVENTORY SUMMARY ===\n");
        summary.append(String.format("Report Generated: %s\n", formatDate(new Date())));
        summary.append(String.format("Total Products: %d\n", products.size()));
        
        BigDecimal totalValue = BigDecimal.ZERO;
        for (BigDecimal value : categoryTotals.values()) {
            totalValue = totalValue.add(value);
        }
        summary.append(String.format("Total Inventory Value: $%.2f\n", totalValue));
        
        summary.append("\n=== CATEGORY BREAKDOWN ===\n");
        for (Map.Entry<String, BigDecimal> entry : categoryTotals.entrySet()) {
            summary.append(String.format("%-20s: $%10.2f\n", 
                    entry.getKey(), entry.getValue()));
        }
        
        return summary.toString();
    }
}