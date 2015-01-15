package com.pwr.suspy.service;

import com.pwr.suspy.domain.Team;
import com.pwr.suspy.domain.User;
import com.pwr.suspy.repository.Teams;
import com.pwr.suspy.service.generic.GenericServiceImpl;
import com.pwr.suspy.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.DTDConstants;
import java.util.ArrayList;
import java.util.List;

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

    public Team findById(long id){return  repository.findById(id);}
    @Transactional(readOnly = true)
    public Page<Team> findEvents(String query, Pageable pageable) {
        return repository.findByNameContaining("%" + query + "%", pageable);
    }

    @Transactional(readOnly = true)
    public List<Team> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Team> findMy() {
        List<Team> allTeams = findAll();
        List<Team> myTeams = new ArrayList<Team>();
        Long sessionUserId = MyUtil.getSessionUser().getId();

        for (Team team : allTeams) {
            for (User user : team.getMembers()) {
                if (user.getId() == sessionUserId) {
                    myTeams.add(team);
                    break;
                }
            }
        }

        return myTeams;
    }

}
