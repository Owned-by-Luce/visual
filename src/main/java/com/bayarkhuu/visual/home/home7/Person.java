package com.bayarkhuu.visual.home.home7;

import javafx.scene.paint.Color;

/**
 * Person
 *
 * @author Баярхүү.Лув 2022.03.30 12:36
 */
public final class Person {
    private final String firstName;
    private final String lastName;
    private Color color;
    private Sport sport;
    private final double year;
    private final boolean vegetarian;

    public Person(String firstName, String lastName, Color color, Sport sport, double year, boolean vegetarian) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.color = color;
        this.sport = sport;
        this.year = year;
        this.vegetarian = vegetarian;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Color getColor() {
        return color;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getYear() {
        return year;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }
}
