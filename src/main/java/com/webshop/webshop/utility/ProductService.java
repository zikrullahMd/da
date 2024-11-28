package com.webshop.webshop.utility;
import com.webshop.webshop.ApplicationConstants;
import com.webshop.webshop.model.ProductModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.webshop.webshop.ApplicationConstants.product;

@Service
public class ProductService {
    public ProductModel getProductById(int id) {
        ProductModel result = null;
        for(int i = 0; i< product.size(); i++){
            if(product.get(i).getId() == id){
                result = product.get(i);
            }
        }

        return result;
    }

    public ProductModel isProductPresent(String name, int price, int size, String color){
        for(ProductModel prod : product){
            if (prod.getName().equals(name) && prod.getPrice() == price && prod.getSize() == size && prod.getColor().equals(color)) {
                return prod;
            }
        }

        return null;
    }

    public ArrayList<ProductModel> deleteProductById(int id) {
        ProductModel productToRemove = product.stream().filter(prod->prod.getId() == id).findFirst().orElse(null);
        if(productToRemove != null){
            product.remove(productToRemove);
            return new ArrayList<>(product);
        }else{
            return null;
        }
    }

    public ProductModel updateProduct(int id, ProductModel updatedProduct) {
        ProductModel existingProduct = getProductById(id);

        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setSize(updatedProduct.getSize());
            existingProduct.setColor(updatedProduct.getColor());
            return existingProduct;
        } else {
            return null;
        }
    }
}
