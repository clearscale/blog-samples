package com.clearscale.legacy.inventory;

import com.clearscale.legacy.inventory.model.Product;
import com.clearscale.legacy.inventory.service.InventoryService;
import com.clearscale.legacy.inventory.util.ReportGenerator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.clearscale.legacy.inventory")
public class InventoryApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(InventoryApplication.class);
        
        InventoryService inventoryService = context.getBean(InventoryService.class);
        ReportGenerator reportGenerator = new ReportGenerator();
        
        // Sample data creation using legacy patterns
        System.out.println("=== Legacy Java 8 Inventory Management System ===");
        
        // Add sample products
        inventoryService.addProduct("Laptop", "Electronics", new BigDecimal("999.99"), 50);
        inventoryService.addProduct("Mouse", "Electronics", new BigDecimal("29.99"), 100);
        inventoryService.addProduct("Keyboard", "Electronics", new BigDecimal("79.99"), 75);
        inventoryService.addProduct("Desk Chair", "Furniture", new BigDecimal("199.99"), 25);
        inventoryService.addProduct("Desk Lamp", "Furniture", new BigDecimal("49.99"), 30);
        
        // Demonstrate legacy operations
        System.out.println("\n" + inventoryService.generateProductReport());
        
        List<Product> lowStock = inventoryService.getLowStockProducts(30);
        System.out.println(String.format("\nLow Stock Products (< 30): %d items", lowStock.size()));
        
        Map<String, BigDecimal> categoryTotals = inventoryService.getCategoryTotals();
        System.out.println("\n" + reportGenerator.generateSummaryReport(
                inventoryService.getAllProducts(), categoryTotals));
        
        // Search demonstration
        List<Product> searchResults = inventoryService.searchProducts("desk");
        System.out.println(String.format("\nSearch results for 'desk': %d items found", 
                searchResults.size()));
        
        context.close();
    }
}