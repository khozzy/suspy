package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.Activity;
import com.pwr.teamfinder.domain.Address;
import com.pwr.teamfinder.domain.Place;
import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.repository.Places;
import com.pwr.teamfinder.service.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GymService extends GenericServiceImpl<Place, Long, Places> {

    @Autowired
    private Places repository;

    @Override
    public Places getRepository() {
        return repository;
    }

    public Place createGym(final String name, final Address address, final User owner, final int capacity, final Set<Activity> activities) {

        Place gym = new Place();

        gym.setName(name);
        gym.setAddress(address);
        gym.setOwner(owner);
        gym.setCapacity(capacity);
        gym.setActivities(activities);

        return repository.save(gym);
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
