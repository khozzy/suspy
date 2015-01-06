package com.pwr.suspy.dto;

/**
 * Created by alapinsk on 2015-01-06.
 */
public class EditEvents {

    private String name;

    private String details;

    private String date_from;

    private String date_to;

    private String organizer;

    private long team;

    private Boolean priv = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

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

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public Boolean getPriv() {
        return priv;
    }

    public void setPriv(Boolean priv) {
        this.priv = priv;
    }

    public long getTeam() {
        return team;
    }

    public void setTeam(long team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "EditEvents{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", date_from='" + date_from + '\'' +
                ", date_to='" + date_to + '\'' +
                ", organizer='" + organizer + '\'' +
                ", team=" + team +
                ", priv=" + priv +
                '}';
    }
}
