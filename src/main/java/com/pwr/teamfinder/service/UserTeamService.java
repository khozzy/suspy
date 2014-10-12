package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.UserTeam;
import com.pwr.teamfinder.repository.UserTeams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserTeamService {

    @Autowired
    private UserTeams repository;

    public UserTeam save(final UserTeam userTeam) {
        return repository.save(userTeam);
    }

    public void someMethod() {
        System.out.println("Calling some method from service UserTeamService");
    }

}
