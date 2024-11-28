package com.webshop.webshop.controller;
import com.webshop.webshop.ApplicationConstants;
import com.webshop.webshop.facade.ProductDetailFacade;
import com.webshop.webshop.model.ProductDetailDTO;
import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.utility.InventoryService;
import com.webshop.webshop.utility.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Random;

import static com.webshop.webshop.ApplicationConstants.product;



@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    InventoryService inventoryService;

    @Autowired
    ProductDetailFacade productDetailFacade;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product-list")
    public String getProduct(@RequestParam(required = false, defaultValue = "false") boolean edit, Model model) {
        for (ProductModel product : ApplicationConstants.product) {
            int updatedStock = inventoryService.getStockForProductId(product.getId());
            product.setStock(updatedStock);
        }

        model.addAttribute("productCatalog", ApplicationConstants.product);
        model.addAttribute("editMode", edit); // Pass the mode to the view
        return "productCatalog";
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getProductByIds(@PathVariable int id) {
//        return getProductById(product, id);
        if (productService.getProductById(id) != null) {
            return ResponseEntity.ok(productService.getProductById(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

//    @GetMapping("/product-detail/{id}")
//    public String getProductDetail(@PathVariable int id, Model model) {
////        ProductModel product = productService.getProductById(id);
////        if (product != null) {
////            int stock = inventoryService.getStockForProductId(id);
////            product.setStock(stock);
////            model.addAttribute("product", product);
////        }
////        return "productDetailPage";
//        // PRODUCT DETAIL DTO
//        ProductModel product = productService.getProductById(id);
//
//        if (product != null) {
//            int stock = inventoryService.getStockForProductId(id);
//            ProductDetailDTO productDetailDTO = new ProductDetailDTO(product, stock);
//            model.addAttribute("productDetailDTO", productDetailDTO);
//        } else {
//            model.addAttribute("errorMessage", "Product not found.");
//        }
//
//        return "productDetailPage";
//    }

    // Using ProductDetailFacade
    @GetMapping("/product-detail/{id}")
    public String getProductDetail(@PathVariable int id, Model model) {
        ProductDetailDTO productDetailDTO = productDetailFacade.getProductDetail(id);
        if (productDetailDTO == null) {
            return "errorPage"; // Return an appropriate error page
        }

        model.addAttribute("productDetailDTO", productDetailDTO);
        return "productDetailPage";
    }

    @PostMapping("/add-product")
    public String addProduct(@RequestParam String name, @RequestParam int price, @RequestParam int size, @RequestParam String color, @RequestParam int stock, HttpServletResponse httpServletResponse) {
        ProductModel existingProduct = productService.isProductPresent(name, price, size, color);
        if (existingProduct != null) {
            return "addProduct";
        }

        int productId = new Random().nextInt(100);
        ProductModel newProduct = new ProductModel(productId, name, price, size, color, stock);
        product.add(newProduct);
        inventoryService.setStock(productId, stock);
        return "redirect:/product-list";
    }


    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<ArrayList<ProductModel>> deleteProduct(@PathVariable int id) {
        ArrayList<ProductModel> updatedProductList = productService.deleteProductById(id);
        if(updatedProductList != null){
            return ResponseEntity.ok(updatedProductList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/product-delete/{id}")
    public String deleteProduct(@PathVariable int id, @RequestParam(required = false, defaultValue = "false") boolean edit) {
        productService.deleteProductById(id);
        return "redirect:/product-list?edit=" + edit; // Redirect back with the same edit mode
    }


    @PutMapping("/update-product/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable int id, @RequestBody ProductModel toUpdateProduct) {
        ProductModel updatedProduct = productService.updateProduct(id, toUpdateProduct);
        if(updatedProduct != null){
            product.add(updatedProduct);
            return ResponseEntity.ok(updatedProduct);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}


