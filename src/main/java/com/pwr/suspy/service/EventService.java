package com.pwr.suspy.service;

import com.pwr.suspy.domain.Event;
import com.pwr.suspy.domain.TimeSlot;
import com.pwr.suspy.dto.AddEvents;
import com.pwr.suspy.repository.Events;
import com.pwr.suspy.service.generic.GenericServiceImpl;
import com.pwr.suspy.util.MyUtil;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventService extends GenericServiceImpl<Event, Long, Events> {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private Events repository;

    @Autowired
    private TimeSlotService timeSlotService;

    @Autowired
    private TeamService teamService;

    @Override
    public Events getRepository() {
        return repository;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Event createNewEvent(AddEvents addEvents) {
        
        Event event = new Event();
        event.setName(addEvents.getName());
        event.setDetails(addEvents.getDetails());
        TimeSlot timeSlot = timeSlotService.findById(addEvents.getTimeSlot());
        event.setTimeSlot(timeSlot);
        timeSlotService.bookTimeSlot(timeSlotService.findById(addEvents.getTimeSlot()));
        event.setTeam(teamService.findById(addEvents.getTeam()));
        event.setPriv(addEvents.getPriv());
        event.setCreatedDate(new Date());
        event.setOrganizer(MyUtil.getSessionUser());

        makePayment(timeSlot.getPrice(),
                addEvents.getToken(),
                timeSlot.getPlace().getName()+" booked from " + timeSlot.getFrom() +" to "+
                timeSlot.getTo() + " by " + MyUtil.getSessionUser().getEmail(),
                String.valueOf(timeSlot.getId()));
        
        repository.save(event);
        return event;
    }

    @Transactional(readOnly = true)
    public List<Event> findAll() {
        return repository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Event> findByNameContaining(String name) {
        return repository.findByNameContaining("%" + name + "%");
    }

    @Transactional(readOnly = true)
    public Page<Event> findEvents(String query, Pageable pageable) {
        return repository.findByNameContaining("%" + query + "%", pageable);
    }

    @Transactional(readOnly = true)
    public Event findOne(long eventID) {
        return repository.getOne(eventID);
    }

    public void makePayment(BigDecimal amount, String token, String description, String timeSlotID) {
        logger.info(token);
        Stripe.apiKey = "sk_test_BQokikJOvBiI2HlWgH4olfQ2";
        Map<String, Object> chargeMap = new HashMap<>();
        chargeMap.put("amount", amount.multiply(BigDecimal.valueOf(100)).intValue());
        chargeMap.put("currency", "usd");
        chargeMap.put("card", token);
        chargeMap.put("description", description);
        Map<String, String> initialMetadata = new HashMap<String, String>();
        initialMetadata.put("order_id", timeSlotID);
        try {
            Charge charge = Charge.create(chargeMap);
            System.out.println(charge);
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}
