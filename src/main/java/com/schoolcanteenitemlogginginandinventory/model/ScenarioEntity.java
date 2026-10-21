package com.scc.model;

public class ScenarioEntity {
    private int id;
    private String itemName;
    private String category;
    private double price;
    private int stock;

    // Constructor kung gikan sa database (naa nay ID)
    public ScenarioEntity(int id, String itemName, String category, double price, int stock) {
        this.id = id;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    // Constructor kung magdugang palamang og bag-o (wala pa'y ID kay auto-increment man)
    public ScenarioEntity(String itemName, String category, double price, int stock) {
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    // Getters para makuha ang mga values
    public int getId() { return id; }
    public String getItemName() { return itemName; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
}