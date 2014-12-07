package com.pwr.suspy.service;

import com.pwr.suspy.domain.Activity;
import com.pwr.suspy.domain.Address;
import com.pwr.suspy.domain.Place;
import com.pwr.suspy.domain.User;
import com.pwr.suspy.dto.AddPlaceForm;
import com.pwr.suspy.dto.AddTimeSlotForm;
import com.pwr.suspy.repository.Places;
import com.pwr.suspy.repository.Users;
import com.pwr.suspy.service.generic.GenericServiceImpl;
import com.pwr.suspy.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@Service
public class PlaceService extends GenericServiceImpl<Place, Long, Places> {

    private Users users;
    @Autowired
    public void setUserService(Users userRepository) {
        this.users = userRepository;
    }
    @Autowired
    private Places repository;

    @Autowired
    private Places places;
    @Override
    public Places getRepository() {
        return repository;
    }

    public Place createNewGym(final String name, final Address address, final User owner, final int capacity, final Set<Activity> activities) {

        Place gym = new Place();

        gym.setName(name);
        gym.setAddress(address);
        gym.setOwner(owner);
        gym.setCapacity(capacity);
        gym.setActivities(activities);

        return repository.save(gym);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Place createNewGym(Place place)
    {
//TODO: SEND MAIL TO ADMIN WITH WITH NEW PLACE NOTIFICATION TO REJECT OR ACCEPT IT;

        places.save(place);
        return place;
    }

    public Place convertAddPlaceFormToPlace(AddPlaceForm addPlaceForm)
    {
        Address gymAddress = new Address(addPlaceForm.getStreet(),addPlaceForm.getHouseNumber(),addPlaceForm.getCity());
        User loggedIn = MyUtil.getSessionUser();
        Place gym = new Place();
        gym.setName(addPlaceForm.getName());
        gym.setAddress(gymAddress);
        gym.setOwner(loggedIn);
        gym.setCapacity(addPlaceForm.getCapacity());
        gym.setActivities(addPlaceForm.getActivities());
        return gym;

    }

    public void acceptGym(final Place gym) {
        gym.setAccepted(true);
        repository.save(gym);
    }

    public void rejectGym(final Place gym) {
        gym.setAccepted(false);
        repository.save(gym);
    }
}
