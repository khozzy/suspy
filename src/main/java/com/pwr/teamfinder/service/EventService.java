package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.Event;
import com.pwr.teamfinder.generic.service.GenericServiceImpl;
import com.pwr.teamfinder.repository.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService extends GenericServiceImpl<Event, Long, Events> {

    @Autowired
    private Events repository;

    @Override
    public Events getRepository() {
        return repository;
    }

    public void someMethod() {
        System.out.println("Calling some method from service EventService");
    }

}
