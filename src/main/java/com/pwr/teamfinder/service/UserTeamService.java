package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.UserTeam;
import com.pwr.teamfinder.generic.service.GenericServiceImpl;
import com.pwr.teamfinder.repository.UserTeams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTeamService extends GenericServiceImpl<UserTeam, Long, UserTeams> {

    @Autowired
    private UserTeams repository;

    @Override
    public UserTeams getRepository() {
        return repository;
    }

    public void someMethod() {
        System.out.println("Calling some method from service UserTeamService");
    }

}
