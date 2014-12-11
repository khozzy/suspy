package com.pwr.suspy.service;

import com.pwr.suspy.domain.Event;
import com.pwr.suspy.repository.Events;
import com.pwr.suspy.service.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService extends GenericServiceImpl<Event, Long, Events> {

    @Autowired
    private Events repository;

    @Override
    public Events getRepository() {
        return repository;
    }

    @Transactional(readOnly = true)
    public Page<Event> findByNameContaining(String name, Pageable pageable) {
        return repository.findByNameContaining(name, pageable);
    }
}
