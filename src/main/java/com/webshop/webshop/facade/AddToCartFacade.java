package com.webshop.webshop.facade;

import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.model.ShoppingCart;
import com.webshop.webshop.utility.InventoryService;
import com.webshop.webshop.utility.ProductService;
import com.webshop.webshop.utility.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
//public class AddToCartFacade {
//
//    private final ProductService productService;
//    private final InventoryService inventoryService;
//    private final ShoppingCartService shoppingCartService;
//
//    @Autowired
//    public AddToCartFacade(ProductService productService, InventoryService inventoryService, ShoppingCartService shoppingCartService) {
//        this.productService = productService;
//        this.inventoryService = inventoryService;
//        this.shoppingCartService = shoppingCartService;
//    }
//
//    public ShoppingCart addToCart(Long productId) {
//        ProductModel product = productService.getProductById(productId.intValue());
//
//        if (product == null) {
//            throw new IllegalArgumentException("Product not found.");
//        }
//
//        // Check if there's enough stock
//        int availableStock = inventoryService.getStockForProductId(product.getId());
//        if (availableStock <= 0) {
//            throw new IllegalStateException("Product is out of stock.");
//        }
//
//        // Reduce stock and add product to the cart
//        inventoryService.reduceStockForProductId(product.getId());
//        shoppingCartService.getCart().getProducts().put(product, shoppingCartService.getCart().getProducts().getOrDefault(product, 0) + 1);
//
//        return shoppingCartService.getCart();
//    }
//}


@Service
public class AddToCartFacade {

    private final ProductService productService;
    private final InventoryService inventoryService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public AddToCartFacade(ProductService productService, InventoryService inventoryService, ShoppingCartService shoppingCartService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
        this.shoppingCartService = shoppingCartService;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCartService.getCart();
    }

    public ShoppingCart addToCart(int productId) {
        ProductModel product = productService.getProductById(productId);

        if (product == null) {
            throw new IllegalArgumentException("Product not found.");
        }

        // Check if there's enough stock
        int availableStock = inventoryService.getStockForProductId(product.getId());
        if (availableStock <= 0) {
            throw new IllegalStateException("Product is out of stock.");
        }

        // Reduce stock and add product to the cart
        inventoryService.reduceStockForProductId(product.getId());

        // Add or update product quantity in the shopping cart
        ShoppingCart cart = shoppingCartService.getCart();
        cart.getProducts().put(product, cart.getProducts().getOrDefault(product, 0) + 1);

        return cart;
    }
}