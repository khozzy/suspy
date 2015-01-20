package com.pwr.suspy.controller.rest;

import com.pwr.suspy.domain.Team;
import com.pwr.suspy.service.TeamService;
import com.pwr.suspy.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Tomasz on 2015-01-13.
 */
@RestController
@RequestMapping("/service/teams")
public class TeamServiceController {

    private TeamService teamService;

    @Autowired
    public TeamServiceController(TeamService teamService) {
        this.teamService = teamService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Collection<Team> getAllTeams() {
        return teamService.findAll();
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    //@RequestMapping(HttpStatus.OK)
    public Collection<Team> getMyTeams() { return teamService.findMy(); }

    @RequestMapping(value = "/{teamID}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Team> getTeam (@PathVariable("teamID") Long teamID) {

        if (teamService.exists(teamID)) {
            return new ResponseEntity<>(teamService.findById(teamID), new HttpHeaders(), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
