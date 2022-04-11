package com.bayarkhuu.visual.labs.lab9;

import java.sql.SQLException;

public class TableViewConnector {

    public static void main(String[] args) throws SQLException {
        Person entity = new Person("Bayarkhuu", "Luvsankhuu");

        Repository<Person> repository = new Repository<>(Person.class);
        System.out.println(repository.findById(repository.save(entity)).getFirstName());
    }
}
