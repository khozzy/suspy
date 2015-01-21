package com.pwr.suspy.controller.rest;

import com.pwr.suspy.domain.Team;
import com.pwr.suspy.service.TeamService;
import com.pwr.suspy.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @RequestMapping(method = RequestMethod.GET, headers = "accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public Page<Team> getTeams(
            @RequestParam(value = "query", defaultValue = "Suspy is the best") String query,
            @RequestParam(value = "pageNum", defaultValue = "0") Long pageNum,
            @RequestParam(value = "numOfResults", defaultValue = "10") Long numOfResults)
    {

        return teamService.findTeams(
                query,
                new PageRequest(
                        pageNum.intValue(),
                        numOfResults.intValue(),
                        new Sort(Sort.Direction.ASC, "name")));
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
