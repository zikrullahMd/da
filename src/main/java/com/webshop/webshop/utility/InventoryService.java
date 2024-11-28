package com.webshop.webshop.utility;

import com.webshop.webshop.ApplicationConstants;
import com.webshop.webshop.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {
    private static Map<Integer, Integer> inventory = new HashMap<>();

    static {
        // Initialize the inventory with stock from the product list
        for (ProductModel p : ApplicationConstants.product) {
            inventory.put(p.getId(), p.getStock());
        }
    }

    private final ProductService productService;

    @Autowired
    public InventoryService(ProductService productService) {
        this.productService = productService;
    }

    public int getStockForProductId(int id) {
        return inventory.getOrDefault(id, -1);
    }

    public void reduceStockForProductId(int id) {
        if (inventory.containsKey(id)) {
            int currentStock = inventory.get(id);
            if (currentStock > 0) {
                inventory.put(id, currentStock - 1);
            } else {
                throw new IllegalStateException("Stock for product " + id + " is out of stock.");
            }
        } else {
            throw new IllegalArgumentException("Product ID " + id + " not found in inventory.");
        }
    }


    public void setStock(int id, int stock) {
        if (productService.getProductById(id) != null) {
            inventory.put(id, stock);
        } else {
            throw new IllegalArgumentException("Product ID " + id + " not found. Cannot set stock.");
        }
    }

}