package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.Team;
import com.pwr.teamfinder.repository.Teams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamService {

    @Autowired
    private Teams repository;

    public Team save(final Team team) {
        return repository.save(team);
    }

    public void someMethod() {
        System.out.println("Calling some method from service TeamService");
    }

}
