package com.bayarkhuu.visual.labs.lab9;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String v) {
        return LocalDate.parse(v, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

    @Override
    public String marshal(LocalDate v) {
        return v.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}
