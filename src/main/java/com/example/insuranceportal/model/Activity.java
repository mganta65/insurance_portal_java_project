package com.example.insuranceportal.model;

import java.time.LocalDate;

/** Simple holder used only to show "Recent Activities" on the dashboard. */
public class Activity {
    private final String title;
    private final String detail;
    private final String amount;
    private final LocalDate date;

    public Activity(String title, String detail, String amount, LocalDate date) {
        this.title = title;
        this.detail = detail;
        this.amount = amount;
        this.date = date;
    }

    public String getTitle() { return title; }
    public String getDetail() { return detail; }
    public String getAmount() { return amount; }
    public LocalDate getDate() { return date; }
}
