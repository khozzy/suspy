package com.pwr.teamfinder.domain;

import com.pwr.teamfinder.generic.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "_timeSlot")
public class TimeSlot extends BaseEntity{

    @Column(name = "dateFrom", nullable = false)
    @NotNull
    private Date dateFrom;

    @Column(name = "dateTo", nullable = false)
    @NotNull
    private Date dateTo;

    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    @Column(name = "eventID")
    @NotNull
    private Long eventID;

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }
}
