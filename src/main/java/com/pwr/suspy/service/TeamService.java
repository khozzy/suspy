package com.pwr.suspy.service;

import com.pwr.suspy.domain.Team;
import com.pwr.suspy.domain.User;
import com.pwr.suspy.repository.Teams;
import com.pwr.suspy.repository.Users;
import com.pwr.suspy.service.generic.GenericServiceImpl;
import com.pwr.suspy.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.DTDConstants;
import java.lang.reflect.Member;
import java.util.*;

@Service
public class TeamService extends GenericServiceImpl<Team, Long, Teams> {

    @Autowired
    private Teams repository;

    @Autowired
    private UserService userService;

    @Autowired
    private Users usersRepository;

    @Override
    public Teams getRepository() {
        return repository;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Team createNewTeam(final String name) {
        Team team = new Team();
        User user = userService.findById(MyUtil.getSessionUser().getId());
        team.setName(name);
        team.setLeader(user);
        team.setCreatedDate(new Date());
        repository.save(team);
        addUserToTeam(team, user);
        return team;
    }

    public void removeTeam(final Team team) {
        Team updatedTeam = repository.findById(team.getId());
        updatedTeam.setDeleted(true);
        updatedTeam.setDeletedDate(new Date());
        repository.save(updatedTeam);
    }

    public void addUserToTeam(final Team team, final User user) {
        User updatedUser = usersRepository.getOne(user.getId());
        updatedUser.getTeams().add(team);
        usersRepository.save(updatedUser);
    }

    public boolean removeUserFromTeam(final Team team, final User user) {
        User updatedUser = usersRepository.getOne(user.getId());
        if (updatedUser.getTeams().remove(team)) {
            usersRepository.save(updatedUser);
            return true;
        }
        return false;
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
            if (team.isDeleted())
                continue;
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
