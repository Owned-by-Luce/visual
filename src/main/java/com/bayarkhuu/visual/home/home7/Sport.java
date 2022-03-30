package com.bayarkhuu.visual.home.home7;

public enum Sport {
    Snowboarding("Snowboarding"), Rowing("Rowing"), Knitting("Knitting"), SpeedReading("Speed reading"), Pool("Pool"), none("None of the above");

    private final String name;

    Sport(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
