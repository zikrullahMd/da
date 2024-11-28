package com.webshop.webshop.utility;

import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    @Autowired
    InventoryService inventoryService;
    private final ShoppingCart cart = new ShoppingCart();
    private final ProductService productService;

    public ShoppingCartService(ProductService productService) {
        this.productService = productService;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void addProductToCart(int productId) {
        ProductModel product = productService.getProductById(productId);

        if (product != null) {
            try {
                inventoryService.reduceStockForProductId(productId);
                cart.products.put(product, cart.products.getOrDefault(product, 0) + 1);
            } catch (IllegalStateException e) {
                System.out.println("Unable to add product to cart: " + e.getMessage());
            }
        } else {
            System.out.println("Product not found.");
        }
    }
}
