package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.Gym;
import com.pwr.teamfinder.domain.TimeSlot;
import com.pwr.teamfinder.repository.TimeSlots;
import com.pwr.teamfinder.service.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class TimeSlotService extends GenericServiceImpl<TimeSlot, Long, TimeSlots> {

    @Autowired
    private TimeSlots repository;

    @Override
    public TimeSlots getRepository() {
        return repository;
    }

    public TimeSlot addTimeSlot(final Gym gym, final Date from, final Date to, final BigDecimal price) {
        // Dodanie slotu czasowego do obiektu sportowego
        return null;
    }

}
