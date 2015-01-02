package com.pwr.suspy.service;

import com.pwr.suspy.domain.*;
import com.pwr.suspy.dto.AddPlaceForm;
import com.pwr.suspy.dto.EditPlaceForm;
import com.pwr.suspy.repository.Places;
import com.pwr.suspy.service.generic.GenericServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PlaceService extends GenericServiceImpl<Place, Long, Places> {

    private static final Logger logger = LoggerFactory.getLogger(PlaceService.class);


    @Autowired
    private Places repository;

    @Autowired
    private TimeSlotService timeSlotService;
    @Override
    public Places getRepository() {
        return repository;
    }

    public Place createNewPlace(
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
    public Place createNewPlace(Place place) {
        //TODO: SEND MAIL TO ADMIN WITH WITH NEW PLACE NOTIFICATION TO REJECT OR ACCEPT IT
        repository.save(place);
        return place;
    }
    public Place editPlace(EditPlaceForm editPlaceForm, Place place)
    {
        Address placeAddress = new Address(
                editPlaceForm.getStreet(),
                editPlaceForm.getHouseNumber(),
                editPlaceForm.getCity());
        place.setAddress(placeAddress);
        place.setName(editPlaceForm.getName());
        place.setCapacity(Integer.parseInt(editPlaceForm.getCapacity()));
        repository.save(place);
        return place;
    }
    public Place getPlace(AddPlaceForm addPlaceForm, User owner) {
        Address placeAddress = new Address(
                addPlaceForm.getStreet(),
                addPlaceForm.getHouseNumber(),
                addPlaceForm.getCity());

        // Activities mock
        Set<Activity> activities = new HashSet<>();
        activities.add(Activity.BASKETBALL);

        Place place = new Place();

        place.setName(addPlaceForm.getName());
        place.setAddress(placeAddress);
        place.setOwner(owner);
        place.setCapacity(Integer.parseInt(addPlaceForm.getCapacity()));
        place.setCreatedDate(new Date());
//        TODO: zmienione
//        place.setActivities(addPlaceForm.getActivities());

        place.setActivities(activities);
        repository.save(place);
        String[] listOfTimeSlots = addPlaceForm.getTimeSlotList().split(";");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        logger.info(Integer.toString(listOfTimeSlots.length));
        for (int i = 0; i < listOfTimeSlots.length; i++) {
//            try{
            String[] timeSlotArgs = listOfTimeSlots[i].split(",");
            TimeSlot newTimeSlot = timeSlotService.addTimeSlot(dateFormat,place,timeSlotArgs[0],timeSlotArgs[1],timeSlotArgs[2]);
//                        new TimeSlot();
//                newTimeSlot.setPlace(place);
//                newTimeSlot.setCreatedDate(new Date());
//                newTimeSlot.setFrom(dateFormat.parse(timeSlotArgs[0]));
//                newTimeSlot.setTo(dateFormat.parse(timeSlotArgs[1]));
//                newTimeSlot.setPrice(new BigDecimal(Float.parseFloat(timeSlotArgs[2])));
//            }catch (ParseException ex){
//
//            }
        }

        return place;
    }

    public void acceptPlace(final Place place) {
        place.setAccepted(true);
        repository.save(place);
    }

    public void rejectGym(final Place place) {
        place.setAccepted(false);
        repository.save(place);
    }

    public Place findById(long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Place> findByNameContaining(String name) {
        return repository.findByNameContaining("%" + name + "%");
    }

    @Transactional(readOnly = true)
    public Page<Place> findPlaces(String query, Pageable pageable) {
        return repository.findByNameContaining("%" + query + "%", pageable);
    }
    public List<Place> findByOwner(User owner) { return repository.findByOwner(owner); }


}
