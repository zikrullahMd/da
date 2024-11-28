package com.webshop.webshop.model;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    public Map<ProductModel, Integer> products = new HashMap<>();

    public double getTotalPrice() {
        return products.entrySet()
                .stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public Map<ProductModel, Integer> getProducts() {
        return this.products;
    }
}
