package com.pwr.suspy.service;

import com.pwr.suspy.domain.Activity;
import com.pwr.suspy.domain.Address;
import com.pwr.suspy.domain.Place;
import com.pwr.suspy.domain.User;
import com.pwr.suspy.dto.AddPlaceForm;
import com.pwr.suspy.dto.AddTimeSlotForm;
import com.pwr.suspy.repository.Places;
import com.pwr.suspy.service.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@Service
public class PlaceService extends GenericServiceImpl<Place, Long, Places> {

    @Autowired
    private Places repository;

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

    public Place convertAddPlaceFormToPlace(AddPlaceForm addPlaceForm)
    {
        Address gymAddress = new Address(addPlaceForm.getStreet(),addPlaceForm.getHouseNumber(),addPlaceForm.getCity());
//TODO: Znajdywać aktualnie zalogowanego usera, (o ile zakladamy ze on jest ownerem skoro dodaje obiekt)
        User owner = new User();
        return createNewGym(addPlaceForm.getName(),gymAddress,owner,addPlaceForm.getCapacity(),addPlaceForm.getActivities());

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
