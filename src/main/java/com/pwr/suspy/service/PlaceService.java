package com.pwr.suspy.service;

import com.pwr.suspy.domain.Activity;
import com.pwr.suspy.domain.Address;
import com.pwr.suspy.domain.Place;
import com.pwr.suspy.domain.TimeSlot;
import com.pwr.suspy.domain.User;
import com.pwr.suspy.dto.AddPlaceForm;
import com.pwr.suspy.repository.Places;
import com.pwr.suspy.service.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PlaceService extends GenericServiceImpl<Place, Long, Places> {

    @Autowired
    private Places repository;

    @Autowired
    private TimeSlotService timeSlotService;
    @Override
    public Places getRepository() {
        return repository;
    }

    public Place createNewGym(
            final String name,
            final Address address,
            final User owner,
            final int capacity,
            final Set<Activity> activities) {

        Place gym = new Place();

        gym.setName(name);
        gym.setAddress(address);
        gym.setOwner(owner);
        gym.setCapacity(capacity);
        gym.setActivities(activities);

        return repository.save(gym);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Place createNewGym(Place place) {
        //TODO: SEND MAIL TO ADMIN WITH WITH NEW PLACE NOTIFICATION TO REJECT OR ACCEPT IT
        repository.save(place);
        return place;
    }

    public Place getPlace(AddPlaceForm addPlaceForm, User owner) {
        Address gymAddress = new Address(
                addPlaceForm.getStreet(),
                addPlaceForm.getHouseNumber(),
                addPlaceForm.getCity());

        // Activities mock
        Set<Activity> activities = new HashSet<>();
        activities.add(Activity.BASKETBALL);

        Place gym = new Place();

        gym.setName(addPlaceForm.getName());
        gym.setAddress(gymAddress);
        gym.setOwner(owner);
        gym.setCapacity(addPlaceForm.getCapacity());
        gym.setCreatedDate(new Date());
//        TODO: zmienione
//        gym.setActivities(addPlaceForm.getActivities());

        gym.setActivities(activities);
        repository.save(gym);
        String[] listOfTimeSlots = addPlaceForm.getTimeSlotList().split(";");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (int i = 0; i < listOfTimeSlots.length; i++) {
            try{
                String[] timeSlotArgs = listOfTimeSlots[i].split(",");
                TimeSlot newTimeSlot = new TimeSlot();
                newTimeSlot.setPlace(gym);
                newTimeSlot.setCreatedDate(new Date());
                newTimeSlot.setFrom(dateFormat.parse(timeSlotArgs[0]));
                newTimeSlot.setTo(dateFormat.parse(timeSlotArgs[1]));
                newTimeSlot.setPrice(new BigDecimal(Float.parseFloat(timeSlotArgs[2])));
                timeSlotService.createNewTimeSlot(newTimeSlot);
            }catch (ParseException ex){

            }
        }

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

    public Place findById(long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Place> findByNameContaining(String name) {
        return repository.findByNameContaining("%" + name + "%");
    }

}
