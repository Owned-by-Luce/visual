package com.bayarkhuu.visual.labs.lab8;

public class Driver {
    private String name;
    private String image;
    private int star;

    public Driver(String name, String image, int star) {
        this.name = name;
        this.image = image;
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getStar() {
        return star;
    }
}
