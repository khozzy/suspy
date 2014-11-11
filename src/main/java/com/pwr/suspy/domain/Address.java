package com.pwr.suspy.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

    public static final int CITY_MIN = 2;
    public static final int CITY_MAX = 50;
    public static final int STREET_MAX = 50;
    public static final int HOUSE_NR_MAX = 10;
    public static final String HOUSE_NR_PATTERN = "[0-9]*";

    @Column(name = "street", length = STREET_MAX)
    private String street;

    @Column(name = "house_number", length = HOUSE_NR_MAX)
    private String houseNumber;

    @Column(name = "city", nullable = false, length = CITY_MAX)
    private String city;

    public Address() {
    }

    public Address(String street, String houseNumber, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
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

        Address rhs = (Address) obj;

        return new EqualsBuilder()
                .append(street, rhs.getStreet())
                .append(houseNumber, rhs.getHouseNumber())
                .append(city, rhs.getCity())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(193, 541)
                .append(street)
                .append(houseNumber)
                .append(city)
                .toHashCode();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
