package com.pwr.suspy.dto;

import javax.validation.constraints.NotNull;

public class AddTimeSlotForm
{
    @NotNull
    private String date_from;
    @NotNull
    private String date_to;
    @NotNull
    private String price;
    @NotNull
    private String place_id;

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String toString()
    {
        String str = new String();
        str += "date_from:" +this.date_from;
        str += "\ndate_to:"+this.date_to;
        str += "\n"+this.price;
        str += "\n"+this.place_id;
        return str;
    }
    //Generate/ Setters(or Getters)
}
