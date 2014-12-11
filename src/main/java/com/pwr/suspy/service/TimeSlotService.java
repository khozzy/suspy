package com.pwr.suspy.service;

import com.pwr.suspy.domain.Event;
import com.pwr.suspy.domain.Place;
import com.pwr.suspy.domain.TimeSlot;
import com.pwr.suspy.dto.AddTimeSlotForm;
import com.pwr.suspy.repository.Events;
import com.pwr.suspy.repository.Places;
import com.pwr.suspy.repository.TimeSlots;
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


@Service
public class TimeSlotService extends GenericServiceImpl<TimeSlot, Long, TimeSlots> {

    @Autowired
    private TimeSlots repository;

    @Autowired
    private EventService eventService;

    @Autowired
    private PlaceService placeService;
    @Override
    public TimeSlots getRepository() {
        return repository;
    }

    public TimeSlot addTimeSlot(final Place gym, final Event event, final Date from, final Date to,
                                final BigDecimal price) {
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setPlace(gym); /* GET PLACE*/
       // timeSlot.setEvent(event); /* GET EVENT */
        timeSlot.setCreatedDate(new Date());
        timeSlot.setFrom(from);
        timeSlot.setTo(to);
        timeSlot.setPrice(price);
        // Dodanie slotu czasowego do obiektu sportowego
        repository.save(timeSlot);
        return timeSlot;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public TimeSlot createNewTimeSlot(TimeSlot timeSlot)
    {
        repository.save(timeSlot);
        return timeSlot;

    }
    public TimeSlot getTimeSlot(final AddTimeSlotForm addTimeSlotForm) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TimeSlot timeSlot = new TimeSlot();
//TODO: ZROBIC ZNAJDOWANIE MIEJSC: POTRZEBA FORMULARZA DO DODAWANIA MIEJSC
//TODO: ZROBIC ZNAJDOWANIE EVENTOW: POTRZEBA FORMULARZA DO DODAWANIA EVENTOW;
        Place place = placeService.getRepository().findByName("Games: League of Legends");
        timeSlot.setPlace(place); /* GET PLACE*/
        //Event event = eventService.findById((long)1);
       // timeSlot.setEvent(event); /* GET EVENT */

        timeSlot.setCreatedDate(new Date());
        timeSlot.setDeleted(false);

        timeSlot.setFrom(dateFormat.parse(addTimeSlotForm.getDate_from()));
        timeSlot.setTo(dateFormat.parse(addTimeSlotForm.getDate_to()));
        timeSlot.setPrice(new BigDecimal(addTimeSlotForm.getPrice()));
        return timeSlot;
    }

}
