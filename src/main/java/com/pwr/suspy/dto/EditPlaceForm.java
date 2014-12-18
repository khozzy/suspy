package com.pwr.suspy.dto;

import com.pwr.suspy.domain.Address;
import com.pwr.suspy.domain.User;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EditPlaceForm
{

    @NotNull
    @NotBlank(message = "{city.error.blank}")
    @Size(min = Address.CITY_MIN, max = Address.CITY_MAX, message = "{city.error.size}")
    @Pattern(regexp = User.REQUIRED_PATTERN, message = "{city.error.pattern}")
    private String city;

    @Size(max = Address.STREET_MAX, message = "{street.error.size}")
    @Pattern(regexp = User.NON_REQUIRED_PATTERN, message = "{street.error.pattern}")
    private String street;

    @Size(max = Address.HOUSE_NR_MAX, message = "{houseNumber.error.size}")
    @Pattern(regexp = Address.HOUSE_NR_PATTERN, message = "{houseNumber.error.pattern}")
    private String houseNumber;

    @NotNull
    @NotBlank(message = "{city.error.blank}")
    @Pattern(regexp ="[0-9]*")
    private String capacity;

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

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @NotNull
    private String name;
}
