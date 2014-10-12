package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.TimeSlot;
import com.pwr.teamfinder.generic.service.GenericServiceImpl;
import com.pwr.teamfinder.repository.TimeSlots;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeSlotService extends GenericServiceImpl<TimeSlot, Long, TimeSlots> {

    @Autowired
    private TimeSlots repository;

    @Override
    public TimeSlots getRepository() {
        return repository;
    }

    public void someMethod() {
        System.out.println("Calling some method from service TimeSlotService");
    }

}
