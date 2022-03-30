package com.bayarkhuu.visual.home.home6.model;

import java.time.LocalDate;

/**
 * Payment
 *
 * @author Баярхүү.Лув 2022.03.30 11:29
 */
public class Payment {
    private Long id;
    private String processedBy;
    private LocalDate date;
    private String processedFor;
    private String firstDayOccupied;
    private String lastDayOccupied;
    private int totalNight;
    private String phoneUse;
    private String amtCharged;
    private String subTotal;
    private String taxRate;
    private String taxAmt;
    private String amtPaid;

    public Long getId() {
        return id;
    }

    public String getProcessedBy() {
        return processedBy;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getProcessedFor() {
        return processedFor;
    }

    public String getFirstDayOccupied() {
        return firstDayOccupied;
    }

    public String getLastDayOccupied() {
        return lastDayOccupied;
    }

    public int getTotalNight() {
        return totalNight;
    }

    public String getPhoneUse() {
        return phoneUse;
    }

    public String getAmtCharged() {
        return amtCharged;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public String getTaxAmt() {
        return taxAmt;
    }

    public String getAmtPaid() {
        return amtPaid;
    }
}
