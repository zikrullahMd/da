package com.webshop.webshop.controller;

import com.webshop.webshop.facade.AddToCartFacade;
import com.webshop.webshop.model.ShoppingCart;
import com.webshop.webshop.utility.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@Controller
//public class ShoppingCartController {
//    @Autowired
//    private ShoppingCartService cartService;
//
//    @GetMapping("/cart")
//    public String viewCart(Model model) {
//        ShoppingCart cart = cartService.getCart();
//        model.addAttribute("cart", cart);
//        model.addAttribute("totalPrice", cart.getTotalPrice());
//        return "cart";
//    }
//
//    @GetMapping("/cart-add/{id}")
//    public String addProductToCart(@PathVariable int id, Model model) {
//        try {
//            cartService.addProductToCart(id);
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", e.getMessage());
//            return "error"; // Create an error page to handle this gracefully.
//        }
//        return "redirect:/cart";
//    }
//}

// ------------------USING ADDTOCARTFACADE------------------

@Controller
public class ShoppingCartController {

    private final AddToCartFacade addToCartFacade;

    @Autowired
    public ShoppingCartController(AddToCartFacade addToCartFacade) {
        this.addToCartFacade = addToCartFacade;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        ShoppingCart cart = addToCartFacade.getShoppingCart();
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "cart";
    }

    @GetMapping("/cart-add/{id}")
    public String addProductToCart(@PathVariable int id, Model model) {
        try {
            addToCartFacade.addToCart(id);
            return "redirect:/cart";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
}