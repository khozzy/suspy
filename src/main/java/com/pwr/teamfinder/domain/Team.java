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

    @Column(name = "members")
    @NotNull
    private User[] members;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "leader") //nullable or not??
    @NotNull
    private User leader;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User[] getMembers() {
        return members;
    }

    public void setMembers(User[] members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }
}
