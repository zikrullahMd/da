package com.webshop.webshop.model;

public class ProductDetailDTO {
    private int id;
    private String name;
    private int price;
    private int size;
    private String color;
    private int stock;
    private boolean isSoldOut;

    public ProductDetailDTO(int id, String name, int price, int size, String color, int stock, boolean isSoldOut) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.stock = stock;
        this.isSoldOut = isSoldOut;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public boolean isSoldOut() { return isSoldOut; }
    public void setSoldOut(boolean soldOut) { isSoldOut = soldOut; }
}