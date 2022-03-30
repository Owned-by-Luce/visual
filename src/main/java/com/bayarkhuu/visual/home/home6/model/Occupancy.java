package com.bayarkhuu.visual.home.home6.model;

import java.time.LocalDate;

/**
 * Occupancy
 *
 * @author Баярхүү.Лув 2022.03.30 09:54
 */
public class Occupancy {
    private Long id;
    private LocalDate date;
    private String processedBy;
    private String processedFor;
    private String room;
    private String rateApplied;
    private String phoneUse;

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getProcessedBy() {
        return processedBy;
    }

    public String getProcessedFor() {
        return processedFor;
    }

    public String getRoom() {
        return room;
    }

    public String getRateApplied() {
        return rateApplied;
    }

    public String getPhoneUse() {
        return phoneUse;
    }
}
