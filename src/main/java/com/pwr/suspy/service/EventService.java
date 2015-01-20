package com.pwr.suspy.service;

import com.pwr.suspy.domain.Event;
import com.pwr.suspy.dto.AddEvents;
import com.pwr.suspy.repository.Events;
import com.pwr.suspy.service.generic.GenericServiceImpl;
import com.pwr.suspy.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class EventService extends GenericServiceImpl<Event, Long, Events> {

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
    public Event createNewEvent(AddEvents AddEvents) {
        Event event = new Event();
        
        event.setName(AddEvents.getName());
        event.setDetails(AddEvents.getDetails());
        event.setTimeSlot(timeSlotService.findById(AddEvents.getTimeSlot()));
        timeSlotService.bookTimeSlot(timeSlotService.findById(AddEvents.getTimeSlot()));
        event.setTeam(teamService.findById(AddEvents.getTeam()));
        event.setPriv(AddEvents.getPriv());
        event.setCreatedDate(new Date());
        event.setOrganizer(MyUtil.getSessionUser());
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
}
