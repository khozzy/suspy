package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.Gym;
import com.pwr.teamfinder.generic.service.GenericServiceImpl;
import com.pwr.teamfinder.repository.SportObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportObjectService extends GenericServiceImpl<Gym, Long, SportObjects> {

    @Autowired
    private SportObjects repository;

    @Override
    public SportObjects getRepository() {
        return repository;
    }

    public void someMethod() {
        System.out.println("Calling some method from service SportObjectService");
    }

}
