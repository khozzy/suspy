package com.pwr.suspy.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "event")
public class Event extends BaseEntity {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @OneToOne
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organizer")
    private User organizer;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    //@ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
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

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
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

        Event rhs = (Event) obj;

        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(name, rhs.getName())
                .append(priv, rhs.getPriv())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(193, 541)
                .appendSuper(super.hashCode())
                .append(name)
                .append(priv)
                .toHashCode();
    }
    
}
