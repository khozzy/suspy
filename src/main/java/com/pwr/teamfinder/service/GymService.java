package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.Address;
import com.pwr.teamfinder.domain.Gym;
import com.pwr.teamfinder.domain.SportActivity;
import com.pwr.teamfinder.domain.TimeSlot;
import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.repository.Gyms;
import com.pwr.teamfinder.service.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Service
public class GymService extends GenericServiceImpl<Gym, Long, Gyms> {

    @Autowired
    private Gyms repository;

    @Override
    public Gyms getRepository() {
        return repository;
    }

    public Gym createGym(final String name, final Address address, final User owner, final int capacity, final Set<SportActivity> activities) {

        Gym gym = new Gym();

        gym.setName(name);
        gym.setAddress(address);
        gym.setOwner(owner);
        gym.setCapacity(capacity);
        gym.setActivities(activities);

        return repository.save(gym);
    }

    public void acceptGym(final Gym gym) {
        gym.setAccepted(true);
        repository.save(gym);
    }

    public void rejectGym(final Gym gym) {
        gym.setAccepted(false);
        repository.save(gym);
    }
}
