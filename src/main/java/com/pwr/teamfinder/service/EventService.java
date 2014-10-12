package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.Event;
import com.pwr.teamfinder.repository.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventService {

    @Autowired
    private Events repository;

    public Event save(final Event event) {
        return repository.save(event);
    }

    public void someMethod() {
        System.out.println("Calling some method from service EventService");
    }

}
