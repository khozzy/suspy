package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.SportObject;
import com.pwr.teamfinder.repository.SportObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SportObjectService {

    @Autowired
    private SportObjects repository;

    public SportObject save(final SportObject sportObject) {
        return repository.save(sportObject);
    }

    public void someMethod() {
        System.out.println("Calling some method from service SportObjectService");
    }

}
