package com.pwr.suspy.service;

import com.pwr.suspy.domain.Event;
import com.pwr.suspy.repository.Events;
import com.pwr.suspy.service.generic.GenericServiceImpl;
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
