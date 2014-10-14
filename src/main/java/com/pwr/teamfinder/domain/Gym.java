package com.pwr.teamfinder.domain;

import com.pwr.teamfinder.generic.domain.BaseEntity;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "gym")
public class Gym extends BaseEntity {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Address address;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "gym_activity",
            joinColumns = @JoinColumn(name = "gym_id"))
    private Set<SportActivity> activities;

    @NotNull
    @Column(name = "capacity", nullable = false)
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "gym")
    private Set<TimeSlot> timeSlots;

    @NotNull
    @Column(name = "accepted", columnDefinition = "bit(1) DEFAULT b'0'", nullable = false)
    private Boolean accepted = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<SportActivity> getActivities() {
        return activities;
    }

    public void setActivities(Set<SportActivity> activities) {
        this.activities = activities;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(Set<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
