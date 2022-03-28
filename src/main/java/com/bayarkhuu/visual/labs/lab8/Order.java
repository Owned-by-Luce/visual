package com.bayarkhuu.visual.labs.lab8;

public class Order {
    private Long id;
    private boolean vip;
    private String courier;
    private double price;
    private Driver driver;

    public Order(Long id, boolean vip, String courier, double price, Driver driver) {
        this.id = id;
        this.vip = vip;
        this.courier = courier;
        this.price = price;
        this.driver = driver;
    }

    public Long getId() {
        return id;
    }

    public String getCourier() {
        return courier;
    }

    public double getPrice() {
        return price;
    }
}
