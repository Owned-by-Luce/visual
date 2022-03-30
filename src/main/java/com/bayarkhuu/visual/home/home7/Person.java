package com.bayarkhuu.visual.home.home7;

/**
 * Person
 *
 * @author Баярхүү.Лув 2022.03.30 12:36
 */
public record Person(String firstName, String lastName, Color color, Sport sport, double year, boolean vegetarian) {

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

    public double getYear() {
        return year;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }
}
