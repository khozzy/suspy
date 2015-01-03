package com.pwr.suspy.service;

import com.pwr.suspy.domain.Event;
import com.pwr.suspy.repository.Events;
import com.pwr.suspy.service.generic.GenericServiceImpl;
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

    @Override
    public Events getRepository() {
        return repository;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Event createNewEvent(Event event) {
        event.setCreatedDate(new Date());
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
}
