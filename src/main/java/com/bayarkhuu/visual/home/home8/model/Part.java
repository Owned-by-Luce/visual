package com.bayarkhuu.visual.home.home8.model;

import com.bayarkhuu.visual.home.home8.annotation.ForeignKey;

public class Part {
    private int id;
    private String year;
    @ForeignKey
    private Item make;
    @ForeignKey
    private Item model;
    @ForeignKey
    private Item category;
    private String partName;
    private double price;
    private String partNumber;

    public Part() {

    }

    public Part(String year) {
        this.year = year;
    }

    public Part(String year, Item make, Item model, Item category, String partName, double price, String partNumber) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.category = category;
        this.partName = partName;
        this.price = price;
        this.partNumber = partNumber;
    }

    public String getYear() {
        return year;
    }

    public Item getMake() {
        return make;
    }

    public Item getModel() {
        return model;
    }

    public Item getCategory() {
        return category;
    }

    public String getPartName() {
        return partName;
    }

    public double getPrice() {
        return price;
    }

    public String getPartNumber() {
        return partNumber;
    }
}
