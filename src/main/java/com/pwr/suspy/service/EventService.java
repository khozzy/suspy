package com.pwr.suspy.service;

import com.pwr.suspy.domain.Event;
import com.pwr.suspy.repository.Events;
import com.pwr.suspy.service.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService extends GenericServiceImpl<Event, Long, Events> {

    @Autowired
    private Events repository;

    @Override
    public Events getRepository() {
        return repository;
    }

    @Transactional(readOnly = true)
    public List<Event> findByNameContaining(String name) {
        return repository.findByNameContaining("%" + name + "%");
    }
}
