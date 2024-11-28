package com.webshop.webshop;

import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.model.UserModel;

import java.util.ArrayList;
import java.util.Arrays;

public class ApplicationConstants {
    public static final ArrayList<ProductModel> product = new ArrayList<>(Arrays.asList(
            new ProductModel(100, "Nike Shoes", 125, 7, "Blue", 1),
            new ProductModel(101, "Jacket", 75, 40, "Black", 680),
            new ProductModel(102, "Shirt", 250, 42, "White", 520),
            new ProductModel(103, "Trouser", 200, 32, "Blue", 610),
            new ProductModel(104, "Shorts", 25, 34, "Red", 430),
            new ProductModel(105, "Sweater", 80, 38, "Green", 290),
            new ProductModel(106, "Cap", 15, 20, "Yellow", 150),
            new ProductModel(107, "Scarf", 30, 15, "Pink", 230),
            new ProductModel(108, "Socks", 10, 12, "White", 110),
            new ProductModel(109, "Tie", 20, 18, "Black", 180),
            new ProductModel(110, "Leather Belt", 50, 30, "Brown", 370),
            new ProductModel(111, "Handbag", 120, 25, "Beige", 820),
            new ProductModel(112, "Wristwatch", 200, 5, "Silver", 950),
            new ProductModel(113, "Sunglasses", 90, 8, "Black", 480),
            new ProductModel(114, "Earrings", 45, 10, "Gold", 320),
            new ProductModel(115, "Bracelet", 60, 12, "Rose Gold", 410),
            new ProductModel(116, "Anklet", 35, 10, "Silver", 290),
            new ProductModel(117, "Hat", 25, 14, "Red", 270),
            new ProductModel(118, "Gloves", 40, 22, "Brown", 310),
            new ProductModel(119, "Raincoat", 90, 50, "Yellow", 780),
            new ProductModel(120, "Winter Coat", 300, 45, "Grey", 990),
            new ProductModel(121, "Gym Bag", 130, 18, "Blue", 870),
            new ProductModel(122, "Running Shoes", 150, 8, "Black", 640),
            new ProductModel(123, "Sandals", 70, 20, "Brown", 520),
            new ProductModel(124, "T-shirt", 35, 25, "Navy Blue", 310),
            new ProductModel(125, "Jeans", 80, 30, "Dark Blue", 460),
            new ProductModel(126, "Backpack", 110, 28, "Green", 790),
            new ProductModel(127, "Wallet", 55, 15, "Black", 360),
            new ProductModel(128, "Headphones", 180, 5, "Red", 870)
    ));

    public static final ArrayList<UserModel> users = new ArrayList<UserModel>(Arrays.asList(new UserModel(200,"zikrullah","this@gmail.com","1234Zik","9391902529"),new UserModel(201,"arman","arman@gmail.com","1234Arm","9391432529"),new UserModel(202,"yasmin","yas@gmail.com","1234Yas","9391903245"),new UserModel(203,"ahmad","ahmad@gmail.com","1234Ahm","9333302529")));
}
