package com.bayarkhuu.visual.home.home6.model;

/**
 * Room
 *
 * @author Баярхүү.Лув 2022.03.30 10:35
 */
public class Room {
    private Long id;
    private String type;
    private String bedType;
    private int rate;
    private String status;

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBedType() {
        return bedType;
    }

    public int getRate() {
        return rate;
    }

    public String getStatus() {
        return status;
    }
}
