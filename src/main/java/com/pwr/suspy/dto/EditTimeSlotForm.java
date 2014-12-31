package com.pwr.suspy.dto;

public class EditTimeSlotForm
{
    private String date_from;
    private String hour_from;
    private String date_to;
    private String hour_to;
    private String price;

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getHour_from() {
        return hour_from;
    }

    public void setHour_from(String hour_from) {
        this.hour_from = hour_from;
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }

    public String getHour_to() {
        return hour_to;
    }

    public void setHour_to(String hour_to) {
        this.hour_to = hour_to;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
