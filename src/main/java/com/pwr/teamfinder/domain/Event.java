package com.pwr.teamfinder.domain;

import com.pwr.teamfinder.generic.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "_event")
public class Event extends BaseEntity{

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "timeSlotID", nullable = false)
    @NotNull
    private Long timeSlotID;

    @Column(name = "teamID")
    @NotNull
    private Long teamID;

    @Column(name = "priv", nullable = false)
    @NotNull
    private Boolean priv;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTimeSlotID() {
        return timeSlotID;
    }

    public void setTimeSlotID(Long timeSlotID) {
        this.timeSlotID = timeSlotID;
    }

    public Long getTeamID() {
        return teamID;
    }

    public void setTeamID(Long teamID) {
        this.teamID = teamID;
    }

    public Boolean getPriv() {
        return priv;
    }

    public void setPriv(Boolean priv) {
        this.priv = priv;
    }
}
