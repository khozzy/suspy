package com.pwr.suspy.service;

import com.pwr.suspy.domain.Team;
import com.pwr.suspy.domain.User;
import com.pwr.suspy.repository.Teams;
import com.pwr.suspy.service.generic.GenericServiceImpl;
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

    public Team createNewTeam(final String name, final User leader) {
        // Metoda dodajca nowy team
        return null;
    }

    public void removeTeam(final Team team) {
        // Metoda usuwajaca team i wszystkie eventy z nim zwiazane
    }

    public void addUserToTeam(final Team team, final User user) {
        // Metoda dodajaca uzytkownika do teamu
    }

    public void removeUserFromTeam(final Team team, final User user) {
        // Metoda usuwajaca usera z teamu
    }

    public void changeLeader(final Team team, final User newLeader) {
        // Metoda zmieniajaca aktualnego lidera w teamie
    }

}
