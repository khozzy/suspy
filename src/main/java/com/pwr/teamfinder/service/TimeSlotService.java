package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.TimeSlot;
import com.pwr.teamfinder.repository.TimeSlots;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TimeSlotService {

    @Autowired
    private TimeSlots repository;

    public TimeSlot save(final TimeSlot timeSlot) {
        return repository.save(timeSlot);
    }

    public void someMethod() {
        System.out.println("Calling some method from service TimeSlotService");
    }

}
