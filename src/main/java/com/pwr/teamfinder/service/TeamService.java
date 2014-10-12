package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.Team;
import com.pwr.teamfinder.generic.service.GenericServiceImpl;
import com.pwr.teamfinder.repository.Teams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService extends GenericServiceImpl<Team, Long, Teams> {

    @Autowired
    private Teams repository;

    @Override
    public Teams getRepository() {
        return repository;
    }

    public void someMethod() {
        System.out.println("Calling some method from service TeamService");
    }

}
