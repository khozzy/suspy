package com.pwr.suspy.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "place")
public class Place extends BaseEntity {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Address address;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "place_activity",
            joinColumns = @JoinColumn(name = "place_id"))
    private Set<Activity> activities;

    @NotNull
    @Column(name = "capacity", nullable = false)
    private int capacity;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "place")
    private Set<TimeSlot> timeSlots = new HashSet<>();

    @NotNull
    @Column(name = "accepted", columnDefinition = "TINYINT", nullable = false)
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

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        Place rhs = (Place) obj;

        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(name, rhs.getName())
                .append(owner, rhs.getOwner())
                .append(address, rhs.getAddress())
                .append(capacity, rhs.getCapacity())
                .append(accepted, rhs.getAccepted())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(193, 541)
                .appendSuper(super.hashCode())
                .append(name)
                .append(owner)
                .append(address)
                .append(capacity)
                .append(accepted)
                .toHashCode();
    }
}
