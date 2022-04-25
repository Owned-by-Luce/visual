package com.bayarkhuu.visual.home.home8.model;

import com.bayarkhuu.visual.home.home8.annotation.ForeignKey;

public class Part {
    public int id;
    public String year;
    @ForeignKey
    public Item make;
    @ForeignKey
    public Item model;
    @ForeignKey
    public Item category;
    public int quantity;
    public String partName;
    public double price;
    public String partNumber;

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

    public void setYear(String year) {
        this.year = year;
    }

    public Item getMake() {
        return make;
    }

    public void setMake(Item make) {
        this.make = make;
    }

    public Item getModel() {
        return model;
    }

    public void setModel(Item model) {
        this.model = model;
    }

    public Item getCategory() {
        return category;
    }

    public void setCategory(Item category) {
        this.category = category;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    @Override
    public String toString() {
        return year;
    }
}
