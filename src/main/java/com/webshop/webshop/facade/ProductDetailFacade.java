package com.webshop.webshop.facade;

import com.webshop.webshop.model.ProductDetailDTO;
import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.utility.InventoryService;
import com.webshop.webshop.utility.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailFacade {

    private final ProductService productService;
    private final InventoryService inventoryService;

    @Autowired
    public ProductDetailFacade(ProductService productService, InventoryService inventoryService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    public ProductDetailDTO getProductDetail(int productId) {
        ProductModel product = productService.getProductById(productId);
        if (product == null) {
            return null; // Handle null case if needed
        }

        int stock = inventoryService.getStockForProductId(productId);
        boolean isSoldOut = stock <= 0;

        return new ProductDetailDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getSize(),
                product.getColor(),
                stock,
                isSoldOut
        );
    }
}
