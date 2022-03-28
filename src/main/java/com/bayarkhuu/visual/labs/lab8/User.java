package com.bayarkhuu.visual.labs.lab8;

public class User {
    private String firstName;
    private String lastName;
    private String image;

    public User(String firstName, String lastName, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImage() {
        return image;
    }
}
