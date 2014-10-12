package com.pwr.teamfinder.domain;

import com.pwr.teamfinder.generic.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "_team")
public class Team extends BaseEntity{

    @Column(name = "membersIDs")
    @NotNull
    private Long[] membersIDs;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "leaderID") //nullable or not??
    @NotNull
    private Long leaderID;

    public Long[] getMembersIDs() {
        return membersIDs;
    }

    public void setMembersIDs(Long[] membersIDs) {
        this.membersIDs = membersIDs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLeaderID() {
        return leaderID;
    }

    public void setLeaderID(Long leaderID) {
        this.leaderID = leaderID;
    }
}
