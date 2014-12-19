package com.pwr.suspy.service;

import com.pwr.suspy.domain.Event;
import com.pwr.suspy.domain.Place;
import com.pwr.suspy.domain.TimeSlot;
import com.pwr.suspy.dto.AddTimeSlotForm;
import com.pwr.suspy.dto.EditTimeSlotForm;
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
import java.util.List;


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

    public TimeSlot getTimeSlot(final AddTimeSlotForm addTimeSlotForm) throws ParseException, NumberFormatException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TimeSlot timeSlot = new TimeSlot();
        Place place = placeService.findById(Long.parseLong(addTimeSlotForm.getPlace_id()));

        timeSlot.setPlace(place); /* GET PLACE*/

        timeSlot.setCreatedDate(new Date());
        timeSlot.setDeleted(false);

        timeSlot.setFrom(dateFormat.parse(addTimeSlotForm.getDate_from()));
        timeSlot.setTo(dateFormat.parse(addTimeSlotForm.getDate_to()));
        timeSlot.setPrice(new BigDecimal(addTimeSlotForm.getPrice()));
        return timeSlot;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public TimeSlot addTimeSlot(DateFormat dateFormat,Place place,String from,String to,String price)
    {
        TimeSlot newTimeSlot = new TimeSlot();
        try {
            newTimeSlot.setPlace(place);
            newTimeSlot.setCreatedDate(new Date());
            newTimeSlot.setFrom(dateFormat.parse(from));
            newTimeSlot.setTo(dateFormat.parse(to));
            newTimeSlot.setPrice(new BigDecimal(Float.parseFloat(price)));
            repository.save(newTimeSlot);
        }catch (ParseException ex)
        {

        }
        return newTimeSlot;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public TimeSlot editTimeSlot(EditTimeSlotForm editTimeSlotForm, TimeSlot timeSlot){//, String from, String to, String price) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try{
            timeSlot.setFrom(dateFormat.parse(editTimeSlotForm.getDate_from() + " " + editTimeSlotForm.getHour_from()));
            timeSlot.setTo(dateFormat.parse(editTimeSlotForm.getDate_to() + " " + editTimeSlotForm.getHour_to()));
            timeSlot.setPrice(new BigDecimal(Float.parseFloat(editTimeSlotForm.getPrice())));

        }catch (ParseException ex){ }
        repository.save(timeSlot);
        return timeSlot;
    }
    public TimeSlot findById(long id){return  repository.findById(id);}
    public List<TimeSlot> findByPlace(Place place){ return repository.findByPlace(place);}

}
