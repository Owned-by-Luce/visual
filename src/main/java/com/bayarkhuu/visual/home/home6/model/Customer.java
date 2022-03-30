package com.bayarkhuu.visual.home.home6.model;

/**
 * Customer
 *
 * @author Баярхүү.Лув 2022.03.30 09:12
 */
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String emergencyName;
    private String emergencyPhone;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }
}
