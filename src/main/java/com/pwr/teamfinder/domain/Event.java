package com.pwr.teamfinder.domain;

import com.pwr.teamfinder.generic.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "event")
public class Event extends BaseEntity {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @NotNull
    @Column(name = "priv", columnDefinition = "bit(1) DEFAULT b'0'", nullable = false)
    private Boolean priv = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Boolean getPriv() {
        return priv;
    }

    public void setPriv(Boolean priv) {
        this.priv = priv;
    }
}
