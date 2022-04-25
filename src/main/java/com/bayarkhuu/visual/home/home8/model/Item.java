package com.bayarkhuu.visual.home.home8.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String type;
    private String name;

    public Item() {

    }

    public Item(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public Item(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
