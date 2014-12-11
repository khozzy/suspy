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
    private int capacity;

    @NotNull
    private String name;

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
