package com.bayarkhuu.visual.home.home8.model;

import com.bayarkhuu.visual.home.home8.annotation.Json;

import java.util.List;

public class Receipt {
    private int id;
    @Json
    private List<Part> parts;

    public Receipt() {

    }

    public Receipt(List<Part> parts) {
        this.parts = parts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
