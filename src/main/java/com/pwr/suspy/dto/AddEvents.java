package com.pwr.suspy.dto;

/**
 * Created by alapinsk on 2015-01-06.
 */
public class AddEvents {
    
    private String name;

    private long timeSlot;

    private long organizer;
    
    private long team;

    private Boolean priv = false;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(long timeSlot) {
        this.timeSlot = timeSlot;
    }

    public long getOrganizer() {
        return organizer;
    }

    public void setOrganizer(long organizer) {
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
        return "AddEvents{" +
                "name='" + name + '\'' +
                ", timeSlot=" + timeSlot +
                ", organizer=" + organizer +
                ", team=" + team +
                ", priv=" + priv +
                '}';
    }
}
