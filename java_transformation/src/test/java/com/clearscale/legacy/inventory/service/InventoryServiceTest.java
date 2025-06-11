package com.clearscale.legacy.inventory.service;

import com.clearscale.legacy.inventory.model.Product;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class InventoryServiceTest {
    
    private InventoryService inventoryService;
    
    @Before
    public void setUp() {
        inventoryService = new InventoryService();
    }
    
    @Test
    public void testAddProduct() {
        Product product = inventoryService.addProduct("Test Product", "Test Category", 
                new BigDecimal("10.00"), 5);
        
        assertNotNull(product);
        assertNotNull(product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals("Test Category", product.getCategory());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddProductWithNullName() {
        inventoryService.addProduct(null, "Category", new BigDecimal("10.00"), 5);
    }
    
    @Test
    public void testGetProductById() {
        Product product = inventoryService.addProduct("Test Product", "Test Category", 
                new BigDecimal("10.00"), 5);
        
        Optional<Product> found = inventoryService.getProductById(product.getId());
        assertTrue(found.isPresent());
        assertEquals(product.getName(), found.get().getName());
    }
    
    @Test
    public void testGetProductsByCategory() {
        inventoryService.addProduct("Product 1", "Electronics", new BigDecimal("100"), 10);
        inventoryService.addProduct("Product 2", "Electronics", new BigDecimal("200"), 20);
        inventoryService.addProduct("Product 3", "Furniture", new BigDecimal("300"), 30);
        
        List<Product> electronics = inventoryService.getProductsByCategory("Electronics");
        assertEquals(2, electronics.size());
    }
    
    @Test
    public void testUpdateProductQuantity() {
        Product product = inventoryService.addProduct("Test Product", "Test Category", 
                new BigDecimal("10.00"), 5);
        
        boolean updated = inventoryService.updateProductQuantity(product.getId(), 10);
        assertTrue(updated);
        
        Optional<Product> updatedProduct = inventoryService.getProductById(product.getId());
        assertTrue(updatedProduct.isPresent());
        assertEquals(Integer.valueOf(10), updatedProduct.get().getQuantity());
    }
}