package com.pwr.teamfinder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class SportObject {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @NotNull
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "city", nullable = false)
    @NotNull
    private String city;

    @Column(name = "street", nullable = false)
    @NotNull
    private String street;

    @Column(name = "number", nullable = false)
    @NotNull
    private Short number;

    @Column(name = "activities", nullable = false)
    @NotNull
    private Byte[] activities; //in separate file list of activities by numbers

    @Column(name = "capacity", nullable = false)
    @NotNull
    private Byte capacity;

    @Column(name = "owner", nullable = false)
    @NotNull
    private User owner;

    @Column(name = "time_slots", nullable = false)
    @NotNull
    private TimeSlot[] timeSlots;

    @Column(name = "accepted", nullable = false)
    @NotNull
    private Boolean accepted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    public Byte[] getActivities() {
        return activities;
    }

    public void setActivities(Byte[] activities) {
        this.activities = activities;
    }

    public Byte getCapacity() {
        return capacity;
    }

    public void setCapacity(Byte capacity) {
        this.capacity = capacity;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public TimeSlot[] getTime_slots() {
        return timeSlots;
    }

    public void setTime_slots(TimeSlot[] time_slots) {
        this.timeSlots = time_slots;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
