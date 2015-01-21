package com.pwr.suspy.dto;


import com.pwr.suspy.domain.Activity;
import com.pwr.suspy.domain.Address;
import com.pwr.suspy.domain.User;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class AddPlaceForm {

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

    @NotNull
    private String name;

    private String date_from;
    private String hour_from;
    private String date_to;
    private String hour_to;
    @Pattern(regexp ="[0-9]*")
    private String price;
    private int booked;

    @NotNull
    private String timeSlotList;
    private String timeSlotListBox;

    public String getTimeSlotListBox() {
        return timeSlotListBox;
    }

    public void setTimeSlotListBox(String timeSlotListBox) {
        this.timeSlotListBox = timeSlotListBox;
    }

    public String getHour_to() {
        return hour_to;
    }

    public void setHour_to(String hour_to) {
        this.hour_to = hour_to;
    }

    public String getHour_from() {
        return hour_from;
    }

    public void setHour_from(String hour_from) {
        this.hour_from = hour_from;
    }

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTimeSlotList() {
        return timeSlotList;
    }

    public void setTimeSlotList(String timeSlotList) {
        this.timeSlotList = timeSlotList;
    }
//TODO: ADD_ACTIVITTIES w formularzy
//    @NotNull
//    private Set<Activity> activities;

    public Set<Activity> getActivities()
    {
        Set<Activity> bla = new Set<Activity>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Activity> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Activity activity) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Activity> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        bla.add(Activity.BASKETBALL);
        bla.add(Activity.FOOTBALL);
        return bla;
    }
    public String ToString()
    {
        String str = new String();
        str += this.name;
        str += "\n"+this.city;
        str += " "+this.street;
        str += " "+this.houseNumber;
        str += " "+this.timeSlotList;
        return str;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }
}
