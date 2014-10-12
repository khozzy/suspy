package com.pwr.teamfinder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Team {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @NotNull
    private Long id;

    @Column(name = "membersIDs")
    @NotNull
    private Long[] membersIDs;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "leaderID") //nullable or not??
    @NotNull
    private Long leaderID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
