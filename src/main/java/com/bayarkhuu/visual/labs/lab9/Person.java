package com.bayarkhuu.visual.labs.lab9;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Person {
    private Integer id;
    private String firstName;
    private String lastName;
    private String street;
    private int postalCode;
    private String city;
    private LocalDate birthDate;

    /**
     * Default constructor.
     */
    public Person() {
        this(null, null);
    }

    public Person(String firstName, String lastName) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = "some street";
        this.postalCode = 1234;
        this.city = "some city";
        this.birthDate = LocalDate.of(1999, 2, 21);
    }

    public Person(StringProperty firstName, StringProperty lastName, StringProperty street, IntegerProperty postalCode, StringProperty city, ObjectProperty<LocalDate> birthDate) {
        this.firstName = firstName.getValue();
        this.lastName = lastName.getValue();
        this.street = street.getValue();
        this.postalCode = postalCode.getValue();
        this.city = city.getValue();
        this.birthDate = birthDate.getValue();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public StringProperty firstNameProperty() {
        return new SimpleStringProperty(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public StringProperty lastNameProperty() {
        return new SimpleStringProperty(lastName);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}
