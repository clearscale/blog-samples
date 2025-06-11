package com.clearscale.legacy.inventory.service;

import com.clearscale.legacy.inventory.model.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    
    private Map<Long, Product> inventory = new HashMap<>();
    private Long nextId = 1L;

    // Legacy Java 8 patterns for demonstration
    public Product addProduct(String name, String category, BigDecimal price, Integer quantity) {
        // Old-style null checking
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        
        Product product = new Product(name.trim(), category.trim(), price, quantity);
        product.setId(nextId++);
        inventory.put(product.getId(), product);
        
        return product;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(inventory.values());
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(inventory.get(id));
    }

    // Legacy Java 8 Stream usage that can be modernized
    public List<Product> getProductsByCategory(String category) {
        return inventory.values().stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    // Legacy pattern using String.format
    public String generateProductReport() {
        List<String> lines = new ArrayList<>();
        lines.add("=== INVENTORY REPORT ===");
        
        for (Product product : inventory.values()) {
            String line = String.format("ID: %d | Name: %s | Category: %s | Price: $%.2f | Qty: %d",
                    product.getId(),
                    product.getName(),
                    product.getCategory(),
                    product.getPrice(),
                    product.getQuantity());
            lines.add(line);
        }
        
        lines.add(String.format("Total Products: %d", inventory.size()));
        
        return String.join("\n", lines);
    }

    // Legacy approach to filtering and sorting
    public List<Product> getLowStockProducts(int threshold) {
        List<Product> lowStock = new ArrayList<>();
        
        for (Product product : inventory.values()) {
            if (product.getQuantity() < threshold) {
                lowStock.add(product);
            }
        }
        
        // Legacy sorting approach
        Collections.sort(lowStock, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Integer.compare(p1.getQuantity(), p2.getQuantity());
            }
        });
        
        return lowStock;
    }

    // Legacy approach with multiple operations
    public Map<String, BigDecimal> getCategoryTotals() {
        Map<String, BigDecimal> categoryTotals = new HashMap<>();
        
        for (Product product : inventory.values()) {
            String category = product.getCategory();
            BigDecimal value = product.getPrice().multiply(new BigDecimal(product.getQuantity()));
            
            if (categoryTotals.containsKey(category)) {
                categoryTotals.put(category, categoryTotals.get(category).add(value));
            } else {
                categoryTotals.put(category, value);
            }
        }
        
        return categoryTotals;
    }

    // Legacy validation patterns
    public boolean updateProductQuantity(Long productId, Integer newQuantity) {
        if (productId == null) {
            return false;
        }
        
        Product product = inventory.get(productId);
        if (product == null) {
            return false;
        }
        
        if (newQuantity == null || newQuantity < 0) {
            return false;
        }
        
        product.setQuantity(newQuantity);
        product.setLastUpdated(new Date());
        return true;
    }

    // Legacy string processing
    public List<Product> searchProducts(String searchTerm) {
        if (StringUtils.isBlank(searchTerm)) {
            return new ArrayList<>();
        }
        
        List<Product> results = new ArrayList<>();
        String lowerSearchTerm = searchTerm.toLowerCase();
        
        for (Product product : inventory.values()) {
            if (product.getName().toLowerCase().contains(lowerSearchTerm) ||
                product.getCategory().toLowerCase().contains(lowerSearchTerm)) {
                results.add(product);
            }
        }
        
        return results;
    }
}