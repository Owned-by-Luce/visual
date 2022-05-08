package com.bayarkhuu.visual.home.home10;

/**
 * Province
 *
 * @author Баярхүү.Лув 2022.05.08 17:29
 */
public class Province {
    private String name;
    private int population;
    private int current;
    private int size;

    public Province(String name, int population, int current, int size) {
        this.name = name;
        this.population = population;
        this.current = current;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getCurrent() {
        return current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
